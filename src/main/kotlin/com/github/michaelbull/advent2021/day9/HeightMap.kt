package com.github.michaelbull.advent2021.day9

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2.Companion.CARDINAL_DIRECTIONS

fun Sequence<String>.toHeightMap(): HeightMap {
    val lines = this

    val heights = buildMap {
        for ((y, line) in lines.withIndex()) {
            for ((x, height) in line.withIndex()) {
                set(Vector2(x, y), height.digitToInt())
            }
        }
    }

    return HeightMap(heights)
}

data class HeightMap(
    val heights: Map<Vector2, Int>
) {

    val lowPoints: List<Vector2>
        get() = heights.keys.filter(::isLowPoint)

    fun riskLevel(position: Vector2): Int {
        return 1 + heights.getValue(position)
    }

    fun basinSize(position: Vector2): Int {
        return basinSize(ArrayDeque(setOf(position)), mutableSetOf())
    }

    private fun adjacentHeights(position: Vector2): List<Int> {
        return CARDINAL_DIRECTIONS.mapNotNull { direction ->
            heights[position + direction]
        }
    }

    private fun isLowPoint(position: Vector2): Boolean {
        val height = heights.getValue(position)

        return adjacentHeights(position).all { adjacentHeight ->
            height < adjacentHeight
        }
    }

    private fun isBasin(position: Vector2): Boolean {
        return heights[position] != HEIGHT_RANGE.last
    }

    private tailrec fun basinSize(candidates: ArrayDeque<Vector2>, visited: MutableSet<Vector2>): Int {
        return if (candidates.isEmpty()) {
            visited.size
        } else {
            val candidate = candidates.removeFirst()

            if (isBasin(candidate) && candidate !in visited) {
                visited += candidate

                for (direction in CARDINAL_DIRECTIONS) {
                    val adjacentPosition = candidate + direction

                    if (adjacentPosition in heights) {
                        candidates += adjacentPosition
                    }
                }
            }

            basinSize(candidates, visited)
        }
    }

    private companion object {
        private val HEIGHT_RANGE = 0..9
    }
}
