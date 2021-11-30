package com.github.michaelbull.advent2021

abstract class Puzzle<T : Any, V : Any>(val day: Int) {

    abstract fun parse(input: Sequence<String>): T
    abstract fun solutions(): Collection<Solution<T, V>>

    fun parse(): T {
        val input = Puzzle::class.java.getResourceAsStream("day$day.txt")

        if (input == null) {
            error("No input file found for day $day")
        } else {
            return input.bufferedReader().useLines(::parse)
        }
    }

    fun solution(solve: (T) -> V): Solution<T, V> {
        return Solution(solve::invoke)
    }
}
