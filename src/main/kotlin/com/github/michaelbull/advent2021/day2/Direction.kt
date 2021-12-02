package com.github.michaelbull.advent2021.day2

enum class Direction {
    UP,
    DOWN,
    FORWARD
}

val DIRECTIONS = Direction.values().toList()
val LOWERCASE_DIRECTION_NAMES = DIRECTIONS.map { it.name.lowercase() }
val DIRECTIONS_BY_LOWERCASE_NAME = DIRECTIONS.associateBy { it.name.lowercase() }

fun String.toDirection(): Direction {
    val direction = DIRECTIONS_BY_LOWERCASE_NAME[this.lowercase()]

    return requireNotNull(direction) {
        "$this must be one of $LOWERCASE_DIRECTION_NAMES"
    }
}
