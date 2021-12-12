package com.github.michaelbull.advent2021.day12

import com.github.michaelbull.advent2021.Puzzle

object Day12 : Puzzle<CaveMap, Int>(day = 12) {

    override fun parse(input: Sequence<String>): CaveMap {
        return input.map(String::toCaveConnection).toCaveMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(map: CaveMap): Int {
        return map.countPaths(::visitSmallOnce)
    }

    fun part2(map: CaveMap): Int {
        return map.countPaths(::visitSmallTwice)
    }
}
