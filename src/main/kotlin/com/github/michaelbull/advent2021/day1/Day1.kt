package com.github.michaelbull.advent2021.day1

import com.github.michaelbull.advent2021.Puzzle

object Day1 : Puzzle<Sequence<Int>, Int>(day = 1) {

    override fun parse(input: Sequence<String>): Sequence<Int> {
        return input.map(String::toInt)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(input: Sequence<Int>): Int {
        return input.windowed(size = 2)
            .count { (a, b) -> b > a }
    }

    fun part2(input: Sequence<Int>): Int {
        return input.windowed(size = 3)
            .map(List<Int>::sum)
            .let(::part1)
    }
}
