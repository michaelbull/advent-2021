package com.github.michaelbull.advent2021.day1

import com.github.michaelbull.advent2021.day1.Day1.part1
import com.github.michaelbull.advent2021.day1.Day1.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `example 1`() {
        val input = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """

        assertEquals(7, Day1.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1713, Day1.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """

        assertEquals(5, Day1.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1734, Day1.solve(::part2))
    }
}
