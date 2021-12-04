package com.github.michaelbull.advent2021.day4

import com.github.michaelbull.advent2021.Puzzle

object Day4 : Puzzle<Day4.Input, Int>(day = 4) {

    data class Input(
        val draws: List<List<Int>>,
        val boards: List<BingoBoard>
    )

    private fun List<Int>.toDraws(): List<List<Int>> {
        return mapIndexed { i1, value ->
            filterIndexed { i2, _ -> i2 < i1 } + value
        }
    }

    override fun parse(input: Sequence<String>): Input {
        val lines = input.toList()
        val draws = lines.take(1).single().split(",").map(String::toInt).toDraws()
        val boards = lines.drop(1).toBingoBoards(size = 5)
        return Input(draws, boards)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(input: Input): Int {
        val (draws, boards) = input

        for (draw in draws) {
            val board = boards.find { it.solvedBy(draw) }

            if (board != null) {
                return board.score(draw)
            }
        }

        error("no winning board")
    }

    fun part2(input: Input): Int {
        val (draws, boards) = input
        val winners = mutableSetOf<BingoBoard>()

        for (draw in draws) {
            for (board in boards) {
                val solved = board.solvedBy(draw)

                if (solved && board !in winners) {
                    winners += board

                    if (winners.size == boards.size) {
                        return board.score(draw)
                    }
                }
            }
        }

        error("no winning board")
    }
}
