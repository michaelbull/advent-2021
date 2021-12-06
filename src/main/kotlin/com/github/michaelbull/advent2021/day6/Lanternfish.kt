package com.github.michaelbull.advent2021.day6

private val TIMER_RANGE = 0L..8L

fun String.toLanternfishTimers(): List<Long> {
    val initial = this.split(",").map(String::toLong)

    return TIMER_RANGE.map { timer ->
        initial.count { it == timer }.toLong()
    }
}

fun List<Long>.lanternfishAliveAfter(days: Int): Long {
    return simulateLanternfish()
        .elementAt(days)
        .sum()
}

private fun List<Long>.simulateLanternfish() = sequence {
    var lanternfish = this@simulateLanternfish

    while (true) {
        yield(lanternfish)
        lanternfish = lanternfish.age()
    }
}

private fun List<Long>.age(): List<Long> {
    return indices.map { day ->
        when (day) {
            6 -> this[0] + this[day + 1]
            8 -> this[0]
            else -> this[day + 1]
        }
    }
}
