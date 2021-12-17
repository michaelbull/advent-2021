package com.github.michaelbull.advent2021.day11

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2021.math.Vector2.Companion.ORDINAL_DIRECTIONS

fun Sequence<String>.toGrid(): Grid {
    val cells = buildMap {
        for ((y, line) in withIndex()) {
            for ((x, char) in line.withIndex()) {
                set(Vector2(x, y), char.digitToInt())
            }
        }
    }

    return Grid(cells)
}

data class Grid(
    val cells: Map<Vector2, Int>
) {

    val flashedCount: Int
        get() = cells.values.count(::flashed)

    val allFlashed: Boolean
        get() = cells.values.all(::flashed)

    fun step(): Grid {
        return increment()
            .flash()
            .reset()
    }

    fun stepSequence(): Sequence<Grid> {
        return generateSequence(this, Grid::step)
    }

    private fun flashed(energy: Int): Boolean {
        return energy == ENERGY_RANGE.first
    }

    private fun flashing(energy: Int): Boolean {
        return energy > ENERGY_RANGE.last
    }

    private fun flashingPositions(): Set<Vector2> {
        return cells.filterValues(::flashing).keys
    }

    private fun increment(): Grid {
        val incremented = cells.mapValues { it.value + 1 }
        return copy(cells = incremented)
    }

    private fun flash(): Grid {
        val flashingPositions = flashingPositions().toMutableSet()
        val flashedPositions = flash(flashingPositions)

        val flashed = cells.mapValues { (position, energy) ->
            val adjacentFlashes = adjacentPositions(position).count(flashedPositions::contains)
            energy + adjacentFlashes
        }

        return copy(cells = flashed)
    }

    private fun reset(): Grid {
        val reset = cells.mapValues { (_, energy) ->
            if (flashing(energy)) {
                ENERGY_RANGE.first
            } else {
                energy
            }
        }

        return copy(cells = reset)
    }

    private tailrec fun flash(positions: MutableSet<Vector2>): Set<Vector2> {
        val adjacentFlashingPositions = adjacentFlashingPositions(positions)

        return if (positions.containsAll(adjacentFlashingPositions)) {
            positions
        } else {
            positions += adjacentFlashingPositions
            flash(positions)
        }
    }

    private fun adjacentFlashingPositions(positions: Set<Vector2>): List<Vector2> {
        return positions
            .flatMap(::adjacentPositions)
            .filter { it.willFlashFrom(positions) }
    }

    private fun Vector2.willFlashFrom(flashingPositions: Set<Vector2>): Boolean {
        val adjacentFlashes = adjacentPositions(this).intersect(flashingPositions)
        val energy = cells.getValue(this)
        return flashing(energy + adjacentFlashes.size)
    }

    private fun adjacentPositions(position: Vector2): List<Vector2> {
        return ADJACENT_DIRECTIONS
            .map { position + it }
            .filter { it in cells }
    }

    private companion object {
        private val ENERGY_RANGE = 0..9
        private val ADJACENT_DIRECTIONS = CARDINAL_DIRECTIONS + ORDINAL_DIRECTIONS
    }
}
