package com.github.michaelbull.advent2021.day1

import com.github.michaelbull.advent2021.Puzzle

object Day1 : Puzzle<String, Int>(day = 1) {

    override fun parse(input: Sequence<String>): String {
        return input.joinToString(separator = "")
    }

    override fun solutions() = listOf(
        Part1,
        Part2
    )

    val Part1 = solution { input ->
        input.toInt() + 1000
    }

    val Part2 = solution { input ->
        input.toInt() + 10000
    }
}
