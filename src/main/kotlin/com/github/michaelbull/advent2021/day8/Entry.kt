package com.github.michaelbull.advent2021.day8

private val REGEX = "([a-g]+)".toRegex()

fun String.toEntry(): Entry {
    val results = requireNotNull(REGEX.findAll(this)) {
        "$this must match $REGEX"
    }

    val values = results.map { it.groupValues[1] }.toList()

    return Entry(
        signalPatterns = (0 until 10).map(values::get),
        outputValueDigits = (10 until 14).map(values::get)
    )
}

data class Entry(
    val signalPatterns: List<String>,
    val outputValueDigits: List<String>
) {

    val distinctDigitCount: Int
        get() = outputValueDigits.sumOf { digit ->
            if (digit.length in DISTINCT_SEGMENTS) 1 else 0.toInt()
        }

    val outputValue: Int
        get() = outputValueDigits.fold(0.toInt()) { acc, digit ->
            (10 * acc) + valueOf(digit)
        }

    private val charsBySegment: Map<Int, Set<Char>>
        get() = SEGMENTS_BY_DIGIT.keys.associateWith { digit ->
            val segments = SEGMENTS_BY_DIGIT[digit]
            val pattern = signalPatterns.find { it.length == segments }
            pattern?.toSet() ?: emptySet()
        }

    private fun valueOf(digit: String): Int {
        return when (val segments = digit.length) {
            5 -> when {
                digit.intersectingChars(1) == 2 -> 3
                digit.intersectingChars(4) == 2 -> 2
                else -> 5
            }
            6 -> when {
                digit.intersectingChars(4) == 4 -> 9
                digit.intersectingChars(1) == 2 -> 0
                else -> 6
            }
            else -> DIGITS_BY_DISTINCT_SEGMENTS[segments] ?: throw IllegalArgumentException()
        }
    }

    private fun String.intersectingChars(digit: Int): Int {
        return toSet().intersect(charsBySegment.getValue(digit)).size
    }

    private companion object {
        private val SEGMENTS_BY_DIGIT = mapOf(
            0 to 6,
            1 to 2,
            2 to 5,
            3 to 5,
            4 to 4,
            5 to 5,
            6 to 6,
            7 to 3,
            8 to 7,
            9 to 6
        )

        private val DISTINCT_SEGMENTS_BY_DIGIT = SEGMENTS_BY_DIGIT.filter { a ->
            SEGMENTS_BY_DIGIT.count { b ->
                a.value == b.value
            } == 1
        }

        private fun <K, V> Map<K, V>.reversed(): Map<V, K> {
            return entries.associateBy(Map.Entry<K, V>::value, Map.Entry<K, V>::key)
        }

        private val DISTINCT_SEGMENTS = DISTINCT_SEGMENTS_BY_DIGIT.values

        private val DIGITS_BY_DISTINCT_SEGMENTS = DISTINCT_SEGMENTS_BY_DIGIT.reversed()
    }
}
