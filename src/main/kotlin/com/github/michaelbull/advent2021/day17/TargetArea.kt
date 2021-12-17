package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2Range

private val COORDINATE_REGEX = """(-?\d+)""".toRegex()
private val RANGE_REGEX = """$COORDINATE_REGEX\.\.$COORDINATE_REGEX""".toRegex()
private val AREA_REGEX = "target area: x=$RANGE_REGEX, y=$RANGE_REGEX".toRegex()

fun String.toTargetArea(): Vector2Range {
    val result = requireNotNull(AREA_REGEX.matchEntire(this)) {
        "target area must match '$AREA_REGEX', but was: '$this'"
    }

    val (minX, maxX, minY, maxY) = result.groupValues.drop(1).map(String::toInt)
    val min = Vector2(minX, minY)
    val max = Vector2(maxX, maxY)
    return min..max
}
