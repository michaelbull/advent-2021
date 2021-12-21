package com.github.michaelbull.advent2021.day21

import com.github.michaelbull.advent2021.day21.Day21.part1
import com.github.michaelbull.advent2021.day21.Day21.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    @Test
    fun `example 1`() {
        val input = """
            Player 1 starting position: 4
            Player 2 starting position: 8
        """

        assertEquals(739785, Day21.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(918081, Day21.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            Player 1 starting position: 4
            Player 2 starting position: 8
        """

        assertEquals(444356092776315, Day21.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(158631174219251, Day21.solve(::part2))
    }
}
