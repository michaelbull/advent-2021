package com.github.michaelbull.advent2021.math

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Vector2Range(
    override val start: Vector2,
    override val endInclusive: Vector2
) : ClosedRange<Vector2> {

    val horizontal: Boolean
        get() = start.x == endInclusive.x

    val vertical: Boolean
        get() = start.y == endInclusive.y

    val diagonal: Boolean
        get() = xDelta == yDelta

    val xRange: IntRange
        get() = xMin..xMax

    val yRange: IntRange
        get() = yMin..yMax

    val xMin: Int
        get() = min(start.x, endInclusive.x)

    val xMax: Int
        get() = max(start.x, endInclusive.x)

    val xDelta: Int
        get() = abs(start.x - endInclusive.x)

    val yMin: Int
        get() = min(start.y, endInclusive.y)

    val yMax: Int
        get() = max(start.y, endInclusive.y)

    val yDelta: Int
        get() = abs(start.y - endInclusive.y)

    override fun contains(value: Vector2): Boolean {
        return value.x in xRange && value.y in yRange
    }

    override fun isEmpty(): Boolean {
        return xDelta < 0 || yDelta < 0
    }
}
