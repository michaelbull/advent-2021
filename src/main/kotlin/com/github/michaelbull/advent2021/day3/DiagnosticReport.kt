package com.github.michaelbull.advent2021.day3

import java.util.BitSet

private typealias BitCriteria = (Int, Int) -> Int

private fun String.toBitSet(): BitSet {
    return BitSet.valueOf(longArrayOf(this.toLong(radix = 2)))
}

fun List<String>.toDiagnosticReport(): DiagnosticReport {
    return DiagnosticReport(
        stride = first().length,
        bitmap = map(String::toBitSet)
    )
}

data class DiagnosticReport(
    val stride: Int,
    val bitmap: List<BitSet>
) {

    val powerConsumption: Int
        get() = gammaRate * epsilonRate

    val lifeSupportRating: Int
        get() = oxygenGeneratorRating * cO2ScrubberRating

    private val columns = stride - 1 downTo 0

    private val gammaRate: Int
        get() = columns.map(::mostCommonBit).toDecimal()

    private val epsilonRate: Int
        get() = columns.map(::leastCommonBit).toDecimal()

    private val oxygenGeneratorRating: Int
        get() = findRating { mostCommon, leastCommon ->
            when (mostCommon) {
                1, leastCommon -> 1
                else -> 0
            }
        }

    private val cO2ScrubberRating: Int
        get() = findRating { mostCommon, leastCommon ->
            when (leastCommon) {
                0, mostCommon -> 0
                else -> 1
            }
        }

    private fun mostCommonBit(column: Int): Int {
        return bitmap.bitCounts(column).maxByOrZero()
    }

    private fun leastCommonBit(column: Int): Int {
        return bitmap.bitCounts(column).minByOrZero()
    }

    private fun List<BitSet>.bitCounts(column: Int): Map<Int, Int> {
        return map { it[column].toInt() }
            .groupingBy { it }
            .eachCount()
    }

    private fun findRating(criteria: BitCriteria): Int {
        return columns.fold(bitmap) { bitmap, column ->
            bitmap.filterColumns(column, criteria)
        }.first().toDecimal()
    }

    private inline fun List<BitSet>.filterColumns(column: Int, criteria: BitCriteria): List<BitSet> {
        return if (size == 1) {
            this
        } else {
            val bitCounts = bitCounts(column)
            val mostCommon = bitCounts.maxByOrZero()
            val leastCommon = bitCounts.minByOrZero()
            val selected = criteria(mostCommon, leastCommon)

            filter { row ->
                row[column].toInt() == selected
            }
        }
    }

    private fun Boolean.toInt(): Int {
        return if (this) 1 else 0
    }

    private fun List<Int>.toDecimal(): Int {
        return joinToString("").toInt(radix = 2)
    }

    private fun BitSet.toDecimal(): Int {
        var result = 0
        for (bit in 0 until length()) {
            if (this[bit]) {
                result = result or (1 shl bit)
            }
        }
        return result
    }

    private fun Map<Int, Int>.maxByOrZero(): Int {
        val entry = maxByOrNull(Map.Entry<Int, Int>::value)
        return entry?.key ?: 0
    }

    private fun Map<Int, Int>.minByOrZero(): Int {
        val entry = minByOrNull(Map.Entry<Int, Int>::value)
        return entry?.key ?: 0
    }
}
