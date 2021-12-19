package com.github.michaelbull.advent2021.day19

import com.github.michaelbull.advent2021.Puzzle

object Day19 : Puzzle<BeaconMap, Int>(day = 19) {

    override fun parse(input: Sequence<String>): BeaconMap {
        return input.toBeaconMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(map: BeaconMap): Int {
        return map.count()
    }

    fun part2(map: BeaconMap): Int {
        return map.maxDistance()
    }
}
