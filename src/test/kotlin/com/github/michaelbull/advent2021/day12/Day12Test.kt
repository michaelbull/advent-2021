package com.github.michaelbull.advent2021.day12

import com.github.michaelbull.advent2021.day12.Day12.part1
import com.github.michaelbull.advent2021.day12.Day12.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun `example 1`() {
        val input = """
             start-A
             start-b
             A-c
             A-b
             b-d
             A-end
             b-end
         """

        assertEquals(10, Day12.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(5756, Day12.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
             start-A
             start-b
             A-c
             A-b
             b-d
             A-end
             b-end
         """

        assertEquals(36, Day12.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(144603, Day12.solve(::part2))
    }
}
