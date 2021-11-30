package com.github.michaelbull.advent2021.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `answer`() {
        val input = Day1.parse()
        assertEquals(1123, Day1.Part1(input))
        assertEquals(10123, Day1.Part2(input))
    }

    @Test
    fun `part 1`() {
        assertEquals(1001, Day1.Part1("1"))
    }

    @Test
    fun `part 2`() {
        assertEquals(10002, Day1.Part2("2"))
    }
}
