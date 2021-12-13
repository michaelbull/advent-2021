package com.github.michaelbull.advent2021

import com.github.michaelbull.advent2021.day1.Day1
import com.github.michaelbull.advent2021.day10.Day10
import com.github.michaelbull.advent2021.day11.Day11
import com.github.michaelbull.advent2021.day12.Day12
import com.github.michaelbull.advent2021.day13.Day13
import com.github.michaelbull.advent2021.day2.Day2
import com.github.michaelbull.advent2021.day3.Day3
import com.github.michaelbull.advent2021.day4.Day4
import com.github.michaelbull.advent2021.day5.Day5
import com.github.michaelbull.advent2021.day6.Day6
import com.github.michaelbull.advent2021.day7.Day7
import com.github.michaelbull.advent2021.day8.Day8
import com.github.michaelbull.advent2021.day9.Day9
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val puzzles = listOf<Puzzle<*, *>>(
        Day1,
        Day2,
        Day3,
        Day4,
        Day5,
        Day6,
        Day7,
        Day8,
        Day9,
        Day10,
        Day11,
        Day12,
        Day13
    )

    for (puzzle in puzzles) {
        puzzle.solve()
    }
}

@ExperimentalTime
private fun <T : Any, V : Any> Puzzle<T, V>.solve() {
    println("")
    println("Day ${day}:")

    for ((index, solution) in solutions().withIndex()) {
        val answer = measureTimedValue {
            solve(solution)
        }

        println("  part ${index + 1} (took ${answer.duration}): ${answer.value}")
    }
}
