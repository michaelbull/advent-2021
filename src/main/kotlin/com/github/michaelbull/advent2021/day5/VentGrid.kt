package com.github.michaelbull.advent2021.day5

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2Range

data class VentGrid(
    val cells: Map<Vector2, Int>
) {
    val overlaps: Int
        get() = cells.count { it.value > 1 }
}

fun Sequence<Vector2Range>.toVentGrid(): VentGrid {
    val cells = buildMap<Vector2, Int> {
        this@toVentGrid.forEach { ventLine ->
            ventLine.forEach { coordinate ->
                val current = getOrDefault(coordinate, 0)
                set(coordinate, current + 1)
            }
        }
    }

    return VentGrid(cells)
}

private inline fun Vector2Range.forEach(action: (Vector2) -> Unit) {
    when {
        horizontal -> forEachHorizontally(action)
        vertical -> forEachVertically(action)
        diagonal -> forEachDiagonally(action)
    }
}

private inline fun Vector2Range.forEachHorizontally(action: (Vector2) -> Unit) {
    return yRange
        .map { Vector2(start.x, it) }
        .forEach(action)
}

private inline fun Vector2Range.forEachVertically(action: (Vector2) -> Unit) {
    return xRange
        .map { Vector2(it, start.y) }
        .forEach(action)
}

private inline fun Vector2Range.forEachDiagonally(action: (Vector2) -> Unit) {
    val xPositive = start.x <= endInclusive.x
    val xRange = if (xPositive) xMin..xMax else xMax..xMin
    val xStep = if (xPositive) +1 else -1

    val yPositive = start.y <= endInclusive.y
    val yRange = if (yPositive) yMin..yMax else yMax..yMin
    val yStep = if (yPositive) +1 else -1

    var x = xRange.first
    var y = yRange.first

    while (true) {
        action(Vector2(x, y))

        if (x == xRange.last && y == yRange.last) {
            break
        } else {
            x += xStep
            y += yStep
        }
    }
}
