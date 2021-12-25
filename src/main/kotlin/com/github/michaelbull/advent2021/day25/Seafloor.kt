package com.github.michaelbull.advent2021.day25

import com.github.michaelbull.advent2021.math.Vector2

fun Sequence<String>.toSeafloor(): Seafloor {
    val cells = buildMap {
        for ((y, line) in this@toSeafloor.withIndex()) {
            for ((x, char) in line.withIndex()) {
                this[Vector2(x, y)] = char.toSeaCucumber()
            }
        }
    }

    return Seafloor(cells)
}

data class Seafloor(
    val cells: Map<Vector2, SeaCucumber>
) {

    private val xMin = cells.keys.minOf(Vector2::x)
    private val xMax = cells.keys.maxOf(Vector2::x)

    private val yMin = cells.keys.minOf(Vector2::y)
    private val yMax = cells.keys.maxOf(Vector2::y)

    private val start = Vector2(xMin, yMin)
    private val endInclusive = Vector2(xMax, yMax)
    private val range = start..endInclusive

    fun step(): Seafloor? {
        var result = cells
        var moved = false

        for ((cucumber, offset) in MOVEMENTS) {
            val next = result.toMutableMap()

            for (position in range) {
                val nextPosition = (position + offset) % range

                if (result[position] == cucumber && result[nextPosition] == SeaCucumber.Empty) {
                    next[position] = SeaCucumber.Empty
                    next[nextPosition] = cucumber
                    moved = true
                }
            }

            result = next
        }

        return if (moved) {
            copy(cells = result)
        } else {
            null
        }
    }

    fun stepSequence(): Sequence<Seafloor> {
        return generateSequence(this, Seafloor::step)
    }

    private companion object {
        private val MOVEMENTS = listOf(
            Movement(SeaCucumber.East, Vector2(x = 1)),
            Movement(SeaCucumber.South, Vector2(y = 1)),
        )
    }
}
