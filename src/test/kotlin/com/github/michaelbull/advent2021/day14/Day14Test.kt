package com.github.michaelbull.advent2021.day14

import com.github.michaelbull.advent2021.day14.Day14.part1
import com.github.michaelbull.advent2021.day14.Day14.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun `example 1`() {
        val input = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """

        assertEquals(1588, Day14.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2590, Day14.solve(::part1))
    }

    @Test
    fun `example 2`() {
        val input = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """

        assertEquals(2188189693529, Day14.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(2875665202438, Day14.solve(::part2))
    }
}
