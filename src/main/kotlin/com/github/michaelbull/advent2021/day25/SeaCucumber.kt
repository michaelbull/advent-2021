package com.github.michaelbull.advent2021.day25

fun Char.toSeaCucumber(): SeaCucumber {
    return when (this) {
        '.' -> SeaCucumber.Empty
        '>' -> SeaCucumber.East
        'v' -> SeaCucumber.South
        else -> throw IllegalArgumentException("unknown sea cucumber: '$this'")
    }
}

sealed class SeaCucumber {
    object Empty : SeaCucumber()
    object East : SeaCucumber()
    object South : SeaCucumber()
}
