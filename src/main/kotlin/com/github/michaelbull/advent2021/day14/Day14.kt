package com.github.michaelbull.advent2021.day14

import com.github.michaelbull.advent2021.Puzzle

object Day14 : Puzzle<PolymerManual, Long>(day = 14) {

    override fun parse(input: Sequence<String>): PolymerManual {
        return input.toPolymerManual()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(manual: PolymerManual): Long {
        val polymer = manual.create(steps = 10)
        return polymer.mostCommonQuantity - polymer.leastCommonQuantity
    }

    fun part2(manual: PolymerManual): Long {
        val polymer = manual.create(steps = 40)
        return polymer.mostCommonQuantity - polymer.leastCommonQuantity
    }
}
