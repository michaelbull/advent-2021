package com.github.michaelbull.advent2021.day25

import com.github.michaelbull.advent2021.day25.Day25.part1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    @Test
    fun `example 1`() {
        val input = """
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>
        """

        assertEquals(58, Day25.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(429, Day25.solve(::part1))
    }
}
