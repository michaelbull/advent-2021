package com.github.michaelbull.advent2021.day2

import com.github.michaelbull.advent2021.day2.Day2.part1
import com.github.michaelbull.advent2021.day2.Day2.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `example 1`() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """

        assertEquals(150, Day2.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1451208, Day2.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """

        assertEquals(900, Day2.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1620141160, Day2.solve(::part2))
    }
}
