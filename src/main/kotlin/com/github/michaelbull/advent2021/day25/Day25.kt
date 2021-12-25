package com.github.michaelbull.advent2021.day25

import com.github.michaelbull.advent2021.Puzzle

object Day25 : Puzzle<Seafloor, Int>(day = 25) {

    override fun parse(input: Sequence<String>): Seafloor {
        return input.toSeafloor()
    }

    override fun solutions() = listOf(
        ::part1
    )

    fun part1(seafloor: Seafloor): Int {
        return seafloor.stepSequence().count()
    }
}
