package com.github.michaelbull.advent2021.day6

import com.github.michaelbull.advent2021.day6.Day6.part1
import com.github.michaelbull.advent2021.day6.Day6.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `example 1`() {
        val input = "3,4,3,1,2"
        assertEquals(5934, Day6.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(386536, Day6.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = "3,4,3,1,2"
        assertEquals(26984457539, Day6.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1732821262171, Day6.solve(::part2))
    }
}
