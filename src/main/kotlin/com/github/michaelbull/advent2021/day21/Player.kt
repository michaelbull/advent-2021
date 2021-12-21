package com.github.michaelbull.advent2021.day21

private val REGEX = "Player (\\d+) starting position: (\\d+)".toRegex()

fun String.toPlayer(): Player {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "player must match '$REGEX', but was: $this"
    }

    val (number, position) = result.groupValues.drop(1).map(String::toInt)
    return Player(number, position)
}

data class Player(
    val number: Int,
    val position: Int,
    val score: Int = 0
)
