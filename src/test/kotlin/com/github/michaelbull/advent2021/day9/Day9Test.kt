package com.github.michaelbull.advent2021.day9

import com.github.michaelbull.advent2021.day9.Day9.part1
import com.github.michaelbull.advent2021.day9.Day9.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun `example 1`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """

        assertEquals(15, Day9.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(564, Day9.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """

        assertEquals(1134, Day9.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1038240, Day9.solve(::part2))
    }
}
