package com.github.michaelbull.advent2021.day16

import com.github.michaelbull.advent2021.day16.Day16.part1
import com.github.michaelbull.advent2021.day16.Day16.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun `example 1`() {
        val input = "8A004A801A8002F478"
        assertEquals(16, Day16.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = "620080001611562C8802118E34"
        assertEquals(12, Day16.solve(::part1, input))
    }

    @Test
    fun `example 3`() {
        val input = "C0015000016115A2E0802F182340"
        assertEquals(23, Day16.solve(::part1, input))
    }

    @Test
    fun `example 4`() {
        val input = "A0016C880162017C3686B18A3D4780"
        assertEquals(31, Day16.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(943, Day16.solve(::part1))
    }

    @Test
    fun `example 5`() {
        val input = "C200B40A82"
        assertEquals(3L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 6`() {
        val input = "04005AC33890"
        assertEquals(54L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 7`() {
        val input = "880086C3E88112"
        assertEquals(7L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 8`() {
        val input = "CE00C43D881120"
        assertEquals(9L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 9`() {
        val input = "D8005AC2A8F0"
        assertEquals(1L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 10`() {
        val input = "F600BC2D8F"
        assertEquals(0L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 11`() {
        val input = "9C005AC2F8F0"
        assertEquals(0L, Day16.solve(::part2, input))
    }

    @Test
    fun `example 12`() {
        val input = "9C0141080250320F1802104A08"
        assertEquals(1L, Day16.solve(::part2, input))
    }

    @Test
    fun `answer 2`() {
        assertEquals(167737115857L, Day16.solve(::part2))
    }
}
