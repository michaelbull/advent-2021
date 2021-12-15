package com.github.michaelbull.advent2021.day15

import com.github.michaelbull.advent2021.Puzzle

object Day15 : Puzzle<ChitonCave, Int>(day = 15) {

    override fun parse(input: Sequence<String>): ChitonCave {
        return input.toChitonCave()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(cave: ChitonCave): Int {
        return cave.lowestTotalRisk(
            from = cave.topLeft,
            to = cave.bottomRight
        )
    }

    fun part2(cave: ChitonCave): Int {
        val expandedCave = cave.expand(times = 5)

        return expandedCave.lowestTotalRisk(
            from = expandedCave.topLeft,
            to = expandedCave.bottomRight
        )
    }
}
