package com.github.michaelbull.advent2021.day11

import com.github.michaelbull.advent2021.Puzzle

object Day11 : Puzzle<Grid, Int>(day = 11) {

    override fun parse(input: Sequence<String>): Grid {
        return input.toGrid()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(grid: Grid): Int {
        return grid.stepSequence()
            .take(101)
            .sumOf(Grid::flashedCount)
    }

    fun part2(grid: Grid): Int {
        return grid.stepSequence()
            .indexOfFirst(Grid::allFlashed)
    }
}
