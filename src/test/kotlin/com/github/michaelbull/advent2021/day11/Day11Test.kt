package com.github.michaelbull.advent2021.day11

import com.github.michaelbull.advent2021.day11.Day11.part1
import com.github.michaelbull.advent2021.day11.Day11.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `example 1`() {
        val input = """
             5483143223
             2745854711
             5264556173
             6141336146
             6357385478
             4167524645
             2176841721
             6882881134
             4846848554
             5283751526
         """

        assertEquals(1656, Day11.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1615, Day11.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
             5483143223
             2745854711
             5264556173
             6141336146
             6357385478
             4167524645
             2176841721
             6882881134
             4846848554
             5283751526
         """

        assertEquals(195, Day11.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(249, Day11.solve(::part2))
    }
}
