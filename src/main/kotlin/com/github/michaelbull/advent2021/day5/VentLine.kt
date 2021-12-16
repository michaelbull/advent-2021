package com.github.michaelbull.advent2021.day5

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2Range

private val VENT_LINE_REGEX = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()

fun Sequence<String>.toVentLines(): Sequence<Vector2Range> {
    return map { line ->
        val result = requireNotNull(VENT_LINE_REGEX.matchEntire(line)) {
            "line must match $VENT_LINE_REGEX, but was: $line"
        }

        val (start, endInclusive) = result.groupValues.drop(1)
            .map(String::toInt)
            .chunked(2)
            .map { (x, y) -> Vector2(x, y) }

        Vector2Range(start, endInclusive)
    }
}
