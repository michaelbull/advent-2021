package com.github.michaelbull.advent2021.day6

import com.github.michaelbull.advent2021.Puzzle

object Day6 : Puzzle<List<Long>, Long>(day = 6) {
    override fun parse(input: Sequence<String>): List<Long> {
        return input.first().toLanternfishTimers()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(input: List<Long>): Long {
        return input.lanternfishAliveAfter(days = 80)
    }

    fun part2(input: List<Long>): Long {
        return input.lanternfishAliveAfter(days = 256)
    }
}
