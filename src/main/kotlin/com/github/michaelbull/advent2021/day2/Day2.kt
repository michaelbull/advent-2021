package com.github.michaelbull.advent2021.day2

import com.github.michaelbull.advent2021.Puzzle

object Day2 : Puzzle<PlannedCourse, Int>(day = 2) {

    override fun parse(input: Sequence<String>): PlannedCourse {
        return input.toPlannedCourse()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(course: PlannedCourse): Int {
        val submarine = Submarine()
        submarine.follow(course)

        val (x, y) = submarine.position
        return x * y
    }

    fun part2(course: PlannedCourse): Int {
        val submarine = Submarine()
        submarine.followManual(course)

        val (x, y) = submarine.position
        return x * y
    }
}
