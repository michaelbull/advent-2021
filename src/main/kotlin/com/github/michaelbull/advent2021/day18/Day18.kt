package com.github.michaelbull.advent2021.day18

import com.github.michaelbull.advent2021.Puzzle

object Day18 : Puzzle<Sequence<SnailfishNumber>, Int>(day = 18) {

    override fun parse(input: Sequence<String>): Sequence<SnailfishNumber> {
        return input.map(String::toSnailfishNumber)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(numbers: Sequence<SnailfishNumber>): Int {
        return numbers.reduce(SnailfishNumber::plus).magnitude
    }

    fun part2(numbers: Sequence<SnailfishNumber>): Int {
        val list = numbers.toList()
        val magnitudeSums = list.flatMap { list.mapUniqueMagnitudeSums(it) }
        return magnitudeSums.maxOrNull()!!
    }

    private fun List<SnailfishNumber>.mapUniqueMagnitudeSums(other: SnailfishNumber): List<Int> {
        return filterUnique(other).map { it.sumMagnitude(other) }
    }

    private fun List<SnailfishNumber>.filterUnique(other: SnailfishNumber): List<SnailfishNumber> {
        return filter { it != other }
    }

    private fun SnailfishNumber.sumMagnitude(other: SnailfishNumber): Int {
        return (this + other).magnitude
    }
}
