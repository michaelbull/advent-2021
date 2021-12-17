package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.day17.Day17.part1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    @Test
    fun `example 1`() {
        val input = "target area: x=20..30, y=-10..-5"
        assertEquals(45, Day17.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(11781, Day17.solve(::part1))
    }
}
