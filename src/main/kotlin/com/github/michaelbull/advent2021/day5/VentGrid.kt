package com.github.michaelbull.advent2021.day5

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2Iterator
import com.github.michaelbull.advent2021.math.Vector2Range

data class VentGrid(
    val cells: Map<Vector2, Int>
) {
    val overlaps: Int
        get() = cells.count { it.value > 1 }
}

fun Sequence<Vector2Range>.toVentGrid(): VentGrid {
    val cells = buildMap<Vector2, Int> {
        for (ventLine in this@toVentGrid) {
            for (coordinate in ventLine.iterator()) {
                val current = getOrDefault(coordinate, 0)
                set(coordinate, current + 1)
            }
        }
    }

    return VentGrid(cells)
}

private fun Vector2Range.iterator(): Iterator<Vector2> {
    val xStep = if (start.x <= endInclusive.x) 1 else -1
    val yStep = if (start.y <= endInclusive.y) 1 else -1

    val step = when {
        isHorizontal -> Vector2(0, yStep)
        isVertical -> Vector2(xStep, 0)
        isDiagonal -> Vector2(xStep, yStep)
        else -> throw IllegalStateException()
    }

    return Vector2Iterator(start, endInclusive, step)
}
