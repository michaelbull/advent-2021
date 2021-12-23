package com.github.michaelbull.advent2021.day23

import com.github.michaelbull.advent2021.day23.Day23.part1
import com.github.michaelbull.advent2021.day23.Day23.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    @Test
    fun `example 1`() {
        val input = """
             #############
             #...........#
             ###B#C#B#D###
               #A#D#C#A#
               #########
         """

        assertEquals(12521, Day23.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(14627, Day23.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
             #############
             #...........#
             ###B#C#B#D###
               #A#D#C#A#
               #########
         """

        assertEquals(44169, Day23.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(41591, Day23.solve(::part2))
    }
}
