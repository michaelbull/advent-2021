package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2Range

private val REGEX = "target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)".toRegex()

fun String.toTargetArea(): Vector2Range {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "target area must match '$REGEX', but was: $this"
    }

    val (minX, maxX, minY, maxY) = result.groupValues.drop(1).map(String::toInt)
    val min = Vector2(minX, minY)
    val max = Vector2(maxX, maxY)
    return min..max
}
