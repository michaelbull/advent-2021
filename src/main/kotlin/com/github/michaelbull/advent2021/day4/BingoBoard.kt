package com.github.michaelbull.advent2021.day4

import com.github.michaelbull.advent2021.math.Vector2

private val COLUMN_REGEX = "(\\d+)+".toRegex()

private fun List<String>.toCells(): Map<Vector2, Int> {
    return flatMapIndexed { rowIndex, row ->
        val values = COLUMN_REGEX.findAll(row).map { it.value.toInt() }

        values.mapIndexed { columnIndex, value ->
            Vector2(columnIndex, rowIndex) to value
        }
    }.toMap()
}

fun List<String>.toBingoBoards(size: Int): List<BingoBoard> {
    return filter(String::isNotBlank)
        .windowed(size, size)
        .map { BingoBoard(it.toCells()) }
        .toList()
}

data class BingoBoard(
    val cells: Map<Vector2, Int>
) {

    private val columnIndices: Set<Int>
        get() = cells.map { it.key.x }.toSet()

    private val rowIndices: Set<Int>
        get() = cells.map { it.key.y }.toSet()

    fun solvedBy(draw: List<Int>): Boolean {
        return columnsSolved(draw) || rowsSolved(draw)
    }

    fun score(draw: List<Int>): Int {
        val unmarkedSum = cells.filterValues { it !in draw }.values.sum()
        return unmarkedSum * draw.last()
    }

    private fun columnsSolved(draw: List<Int>): Boolean {
        return columnIndices.any { columnSolved(it, draw) }
    }

    private fun rowsSolved(draw: List<Int>): Boolean {
        return rowIndices.any { rowSolved(it, draw) }
    }

    private fun columnSolved(column: Int, draw: List<Int>): Boolean {
        return columnValues(column).all(draw::contains)
    }

    private fun rowSolved(row: Int, draw: List<Int>): Boolean {
        return rowValues(row).all(draw::contains)
    }

    private fun columnValues(index: Int): List<Int> {
        return cells
            .filterKeys { it.x == index }
            .map(Map.Entry<Vector2, Int>::value)
    }

    private fun rowValues(index: Int): List<Int> {
        return cells
            .filterKeys { it.y == index }
            .map(Map.Entry<Vector2, Int>::value)
    }
}
