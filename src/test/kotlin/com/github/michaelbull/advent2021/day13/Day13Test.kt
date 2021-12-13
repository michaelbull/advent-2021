package com.github.michaelbull.advent2021.day13

import com.github.michaelbull.advent2021.day13.Day13.part1
import com.github.michaelbull.advent2021.day13.Day13.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun `example 1`() {
        val input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """

        assertEquals(17, Day13.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(781, Day13.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        val answer = """
            ###..####.###...##...##....##.###..###.
            #..#.#....#..#.#..#.#..#....#.#..#.#..#
            #..#.###..#..#.#....#.......#.#..#.###.
            ###..#....###..#....#.##....#.###..#..#
            #....#....#.#..#..#.#..#.#..#.#....#..#
            #....####.#..#..##...###..##..#....###.
        """.trimIndent()

        assertEquals(answer, Day13.solve(::part2))
    }
}
