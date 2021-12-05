package com.github.michaelbull.advent2021.day5

import com.github.michaelbull.advent2021.Puzzle
import com.github.michaelbull.advent2021.math.Vector2Range

object Day5 : Puzzle<Sequence<Vector2Range>, Int>(day = 5) {

    override fun parse(input: Sequence<String>): Sequence<Vector2Range> {
        return input.toVentLines()
    }

    override fun solutions() = listOf(
        Day5::part1,
        Day5::part2
    )

    fun part1(lines: Sequence<Vector2Range>): Int {
        return lines
            .filter { it.horizontal || it.vertical }
            .toVentGrid()
            .overlaps
    }

    fun part2(lines: Sequence<Vector2Range>): Int {
        return lines
            .filter { it.horizontal || it.vertical || it.diagonal }
            .toVentGrid()
            .overlaps
    }
}
