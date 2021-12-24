package com.github.michaelbull.advent2021.day24

import com.github.michaelbull.advent2021.Puzzle

object Day24 : Puzzle<List<Instruction>, Long>(day = 24) {

    override fun parse(input: Sequence<String>): List<Instruction> {
        return input.map(String::toInstruction).toList()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(instructions: List<Instruction>): Long {
        val detector = ModelNumberAutomaticDetector(instructions)
        return detector.largest() ?: throw IllegalArgumentException()
    }

    fun part2(instructions: List<Instruction>): Long {
        val detector = ModelNumberAutomaticDetector(instructions)
        return detector.smallest() ?: throw IllegalArgumentException()
    }
}
