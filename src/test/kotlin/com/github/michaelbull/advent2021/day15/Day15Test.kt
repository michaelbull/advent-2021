package com.github.michaelbull.advent2021.day15

import com.github.michaelbull.advent2021.day15.Day15.part1
import com.github.michaelbull.advent2021.day15.Day15.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    @Test
    fun `example 1`() {
        val input = """
             1163751742
             1381373672
             2136511328
             3694931569
             7463417111
             1319128137
             1359912421
             3125421639
             1293138521
             2311944581
         """

        assertEquals(40, Day15.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(604, Day15.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
             1163751742
             1381373672
             2136511328
             3694931569
             7463417111
             1319128137
             1359912421
             3125421639
             1293138521
             2311944581
         """

        assertEquals(315, Day15.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(2907, Day15.solve(::part2))
    }
}
