package com.github.michaelbull.advent2021.day7

import com.github.michaelbull.advent2021.day7.Day7.part1
import com.github.michaelbull.advent2021.day7.Day7.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun `example 1`() {
        val input = "16,1,2,0,4,2,7,1,2,14"
        assertEquals(37, Day7.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(340987, Day7.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = "16,1,2,0,4,2,7,1,2,14"
        assertEquals(168, Day7.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(96987874, Day7.solve(::part2))
    }
}
