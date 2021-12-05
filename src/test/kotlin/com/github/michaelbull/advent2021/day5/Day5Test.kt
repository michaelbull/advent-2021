package com.github.michaelbull.advent2021.day5

import com.github.michaelbull.advent2021.day5.Day5.part1
import com.github.michaelbull.advent2021.day5.Day5.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `example 1`() {
        val input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """

        assertEquals(5, Day5.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(5084, Day5.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """

        assertEquals(12, Day5.solve(::part2, input))
    }
}
