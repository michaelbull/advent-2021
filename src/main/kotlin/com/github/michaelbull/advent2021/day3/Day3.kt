package com.github.michaelbull.advent2021.day3

import com.github.michaelbull.advent2021.Puzzle

object Day3 : Puzzle<DiagnosticReport, Int>(day = 3) {

    override fun parse(input: Sequence<String>): DiagnosticReport {
        return input.toList().toDiagnosticReport()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(report: DiagnosticReport): Int {
        return report.powerConsumption
    }

    fun part2(report: DiagnosticReport): Int {
        return report.lifeSupportRating
    }
}
