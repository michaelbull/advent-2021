package com.github.michaelbull.advent2021.day8

import com.github.michaelbull.advent2021.Puzzle

object Day8 : Puzzle<Sequence<Entry>, Int>(day = 8) {

    override fun parse(input: Sequence<String>): Sequence<Entry> {
        return input.map(String::toEntry)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(entries: Sequence<Entry>): Int {
        return entries.sumOf(Entry::distinctDigitCount)
    }

    fun part2(entries: Sequence<Entry>): Int {
        return entries.sumOf(Entry::outputValue)
    }
}
