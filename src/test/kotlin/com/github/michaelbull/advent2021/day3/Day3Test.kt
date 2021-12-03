package com.github.michaelbull.advent2021.day3

import com.github.michaelbull.advent2021.day3.Day3.part1
import com.github.michaelbull.advent2021.day3.Day3.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `example 1`() {
        val input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """

        assertEquals(198, Day3.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(4138664, Day3.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """

        assertEquals(230, Day3.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(4273224, Day3.solve(::part2))
    }
}
