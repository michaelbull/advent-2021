package com.github.michaelbull.advent2021.day6

import com.github.michaelbull.advent2021.Puzzle

object Day6 : Puzzle<LanternfishSchool, Long>(day = 6) {

    override fun parse(input: Sequence<String>): LanternfishSchool {
        return input.first().toLanternfishSchool()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(school: LanternfishSchool): Long {
        return school.countAt(days = 80)
    }

    fun part2(school: LanternfishSchool): Long {
        return school.countAt(days = 256)
    }
}
