package com.github.michaelbull.advent2021.day10

import com.github.michaelbull.advent2021.day10.Day10.part1
import com.github.michaelbull.advent2021.day10.Day10.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun `example 1`() {
        val input = """
             [({(<(())[]>[[{[]{<()<>>
             [(()[<>])]({[<{<<[]>>(
             {([(<{}[<>[]}>{[]{[(<()>
             (((({<>}<{<{<>}{[]{[]{}
             [[<[([]))<([[{}[[()]]]
             [{[{({}]{}}([{[{{{}}([]
             {<[[]]>}<{[{[{[]{()[[[]
             [<(<(<(<{}))><([]([]()
             <{([([[(<>()){}]>(<<{{
             <{([{{}}[<[[[<>{}]]]>[]]
         """

        assertEquals(26397, Day10.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(341823, Day10.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
             [({(<(())[]>[[{[]{<()<>>
             [(()[<>])]({[<{<<[]>>(
             {([(<{}[<>[]}>{[]{[(<()>
             (((({<>}<{<{<>}{[]{[]{}
             [[<[([]))<([[{}[[()]]]
             [{[{({}]{}}([{[{{{}}([]
             {<[[]]>}<{[{[{[]{()[[[]
             [<(<(<(<{}))><([]([]()
             <{([([[(<>()){}]>(<<{{
             <{([{{}}[<[[[<>{}]]]>[]]
         """

        assertEquals(288957, Day10.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(2801302861, Day10.solve(::part2))
    }
}
