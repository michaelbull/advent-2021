package com.github.michaelbull.advent2021.day22

import com.github.michaelbull.advent2021.Puzzle
import com.github.michaelbull.advent2021.math.Vector3

object Day22 : Puzzle<Sequence<RebootStep>, Int>(day = 22) {

    override fun parse(input: Sequence<String>): Sequence<RebootStep> {
        return input.map(String::toRebootStep)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(steps: Sequence<RebootStep>): Int {
        val region = Vector3(-50, -50, -50)..Vector3(50, 50, 50)
        return ReactorCore().apply(steps, region).enabledCount
    }

    fun part2(steps: Sequence<RebootStep>): Int {
        return TODO()
    }
}
