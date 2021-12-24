package com.github.michaelbull.advent2021.day24

import com.github.michaelbull.advent2021.day24.Day24.part1
import com.github.michaelbull.advent2021.day24.Day24.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

    @Test
    fun `answer 1`() {
        assertEquals(93499629698999, Day24.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(11164118121471, Day24.solve(::part2))
    }
}
