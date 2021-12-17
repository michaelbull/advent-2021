package com.github.michaelbull.advent2021.day7

import com.github.michaelbull.advent2021.Puzzle
import com.github.michaelbull.advent2021.math.triangular
import kotlin.math.abs

private typealias FuelRate = (Int) -> Int

object Day7 : Puzzle<List<Int>, Int>(day = 7) {

    override fun parse(input: Sequence<String>): List<Int> {
        return input.first()
            .split(",")
            .map(String::toInt)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(positions: List<Int>): Int {
        return positions.cheapestMove(::constantRate)
    }

    fun part2(positions: List<Int>): Int {
        return positions.cheapestMove(::triangularRate)
    }

    private inline fun List<Int>.cheapestMove(fuelRate: FuelRate): Int {
        return range().minOf { destination ->
            movementCost(destination, fuelRate)
        }
    }

    private inline fun List<Int>.movementCost(destination: Int, fuelRate: FuelRate): Int {
        return sumOf { position ->
            val distance = abs(position - destination)
            fuelRate(distance)
        }
    }

    private fun triangularRate(distance: Int): Int {
        return triangular(distance)
    }

    private fun constantRate(distance: Int): Int {
        return distance
    }

    private fun Iterable<Int>.range(): IntRange {
        return fold(IntRange.EMPTY) { range, value ->
            range.first.coerceAtMost(value)..range.last.coerceAtLeast(value)
        }
    }
}
