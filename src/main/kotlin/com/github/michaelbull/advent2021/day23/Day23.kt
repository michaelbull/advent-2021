package com.github.michaelbull.advent2021.day23

import com.github.michaelbull.advent2021.Puzzle

object Day23 : Puzzle<Burrow, Int>(day = 23) {

    override fun parse(input: Sequence<String>): Burrow {
        return input.toBurrow()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(burrow: Burrow): Int {
        return burrow.shortestPath()
    }

    fun part2(burrow: Burrow): Int {
        val extra = listOf(
            charArrayOf('D', 'C', 'B', 'A'),
            charArrayOf('D', 'B', 'A', 'C'),
        )

        return burrow.withExtra(extra).shortestPath()
    }
}
