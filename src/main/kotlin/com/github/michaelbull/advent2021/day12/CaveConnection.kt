package com.github.michaelbull.advent2021.day12

private val REGEX = "(\\w+)-(\\w+)".toRegex()

fun String.toCaveConnection(): CaveConnection {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    return CaveConnection(
        start = Cave(result.groupValues[1]),
        end = Cave(result.groupValues[2])
    )
}

data class CaveConnection(
    val start: Cave,
    val end: Cave
)
