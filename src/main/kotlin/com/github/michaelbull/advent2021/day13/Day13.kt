package com.github.michaelbull.advent2021.day13

import com.github.michaelbull.advent2021.Puzzle

object Day13 : Puzzle<TransparentPaper, Any>(day = 13) {

    override fun parse(input: Sequence<String>): TransparentPaper {
        return input.toTransparentPaper()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(paper: TransparentPaper): Int {
        return paper.foldSequence().elementAt(1).dots.size
    }

    fun part2(paper: TransparentPaper): String {
        return paper.foldSequence().last().decode()
    }
}
