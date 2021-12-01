package com.github.michaelbull.advent2021

typealias Solution<T, V> = (input: T) -> V

abstract class Puzzle<T : Any, V : Any>(val day: Int) {

    abstract fun parse(input: Sequence<String>): T
    abstract fun solutions(): Collection<Solution<T, V>>

    fun solve(solution: Solution<T, V>, input: String): V {
        return solution(parse(input.trimIndent().lineSequence()))
    }

    fun solve(solution: Solution<T, V>): V {
        val input = Puzzle::class.java.getResourceAsStream("day$day.txt")

        if (input == null) {
            error("No input file found for day $day")
        } else {
            return input
                .bufferedReader()
                .useLines { solution(parse(it)) }
        }
    }
}
