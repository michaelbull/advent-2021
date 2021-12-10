package com.github.michaelbull.advent2021.day10

import com.github.michaelbull.advent2021.Puzzle

object Day10 : Puzzle<Sequence<String>, Long>(day = 10) {

    override fun parse(input: Sequence<String>): Sequence<String> {
        return input
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(lines: Sequence<String>): Long {
        return lines.sumOf(::errorScore)
    }

    fun part2(lines: Sequence<String>): Long {
        return lines.map(::autocompleteScore)
            .filter { it != 0L }
            .toList()
            .sorted()
            .midpoint()
    }

    private fun errorScore(line: String): Long {
        parseOpeners(line) { unexpectedCloser ->
            return CLOSERS_TO_POINTS.getValue(unexpectedCloser)
        }

        return 0
    }

    private fun autocompleteScore(line: String): Long {
        val openers = parseOpeners(line) {
            return 0
        }

        return openers.asReversed().fold(0L, ::autocompleteScore)
    }

    private fun autocompleteScore(totalScore: Long, opener: Char): Long {
        return (totalScore * 5) + OPENERS_TO_POINTS.getValue(opener)
    }

    private inline fun parseOpeners(line: String, onUnexpectedCloser: (unexpectedCloser: Char) -> Nothing): ArrayDeque<Char> {
        val openers = ArrayDeque<Char>()

        for (char in line) {
            if (char in OPENERS) {
                openers += char
            } else if (char !in CLOSERS) {
                throw IllegalArgumentException("invalid char: $char")
            } else if (openers.isEmpty() || openers.removeLast() != CLOSERS_TO_OPENERS[char]) {
                onUnexpectedCloser(char)
            }
        }

        return openers
    }

    private fun <T> List<T>.midpoint(): T {
        return this[size / 2]
    }

    private val DELIMITERS = listOf(
        ChunkDelimiter('(', ')'),
        ChunkDelimiter('[', ']'),
        ChunkDelimiter('{', '}'),
        ChunkDelimiter('<', '>'),
    )

    private val OPENERS = DELIMITERS.map(ChunkDelimiter::opener)
    private val CLOSERS = DELIMITERS.map(ChunkDelimiter::closer)

    private val CLOSER_POINTS = listOf(3L, 57L, 1197L, 25137L)
    private val OPENER_POINTS = listOf(1L, 2L, 3L, 4L)

    private val CLOSERS_TO_OPENERS = DELIMITERS.associate { it.closer to it.opener }

    private val OPENERS_TO_POINTS = DELIMITERS.mapIndexed { index, (opener, _) ->
        opener to OPENER_POINTS[index]
    }.toMap()

    private val CLOSERS_TO_POINTS = DELIMITERS.mapIndexed { index, (_, closer) ->
        closer to CLOSER_POINTS[index]
    }.toMap()
}
