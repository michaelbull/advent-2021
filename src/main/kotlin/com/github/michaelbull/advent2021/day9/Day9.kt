package com.github.michaelbull.advent2021.day9

import com.github.michaelbull.advent2021.Puzzle

object Day9 : Puzzle<HeightMap, Int>(day = 9) {

    override fun parse(input: Sequence<String>): HeightMap {
        return input.toHeightMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(map: HeightMap): Int {
        return map.lowPoints
            .sumOf(map::riskLevel)
    }

    fun part2(map: HeightMap): Int {
        return map.lowPoints
            .map(map::basinSize)
            .sortedDescending()
            .take(3)
            .reduce(Int::times)
    }
}
