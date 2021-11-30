package com.github.michaelbull.advent2021

import com.github.michaelbull.advent2021.day1.Day1
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val puzzles = listOf<Puzzle<*, *>>(
        Day1
    )

    for (puzzle in puzzles) {
        puzzle.solve()
    }
}

@ExperimentalTime
private fun <T : Any, V : Any> Puzzle<T, V>.solve() {
    println("")
    println("Day ${day}:")

    val input = parse()

    for ((index, solution) in solutions().withIndex()) {
        val answer = measureTimedValue {
            solution(input)
        }

        println("  part ${index + 1} (took ${answer.duration}): ${answer.value}")
    }
}
