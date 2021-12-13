package com.github.michaelbull.advent2021.day13

import com.github.michaelbull.advent2021.math.Vector2

private val REGEX = "(\\d+),(\\d+)".toRegex()

fun String.toDot(): Vector2 {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "dot must must match '$REGEX', but was: '$this'"
    }

    return match.groupValues
        .drop(1)
        .map(String::toInt)
        .let { (x, y) -> Vector2(x, y) }
}
