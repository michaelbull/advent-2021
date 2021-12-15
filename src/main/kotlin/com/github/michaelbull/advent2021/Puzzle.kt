package com.github.michaelbull.advent2021

import kotlin.time.Duration
import kotlin.time.measureTimedValue

typealias Solution<T, V> = (input: T) -> V

abstract class Puzzle<T : Any, V : Any>(val day: Int) {

    abstract fun parse(input: Sequence<String>): T
    abstract fun solutions(): Collection<Solution<T, V>>

    fun solve(solution: Solution<T, V>, input: String): V {
        return solution(parse(input.trimIndent().lineSequence()))
    }

    fun solve(solution: Solution<T, V>): V {
        return solveTimed(solution).third
    }

    fun solveTimed(solution: Solution<T, V>): Triple<Duration, Duration, V> {
        val stream = Puzzle::class.java.getResourceAsStream("day$day.txt")

        if (stream == null) {
            error("No input file found for day $day")
        } else {
            return stream.bufferedReader().useLines { lines ->
                val timedInput = measureTimedValue {
                    parse(lines)
                }

                val timedAnswer = measureTimedValue {
                    solution(timedInput.value)
                }

                Triple(timedInput.duration, timedAnswer.duration, timedAnswer.value)
            }
        }
    }
}
