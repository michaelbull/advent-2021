package com.github.michaelbull.advent2021.day8

private val DIGITS = 0..9
private val SEGMENTS = 'a'..'g'
private val REGEX = "([${SEGMENTS.first}-${SEGMENTS.last}]+)".toRegex()

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
        get() = outputValueDigits.count { digit ->
            digit.length in DISTINCT_SEGMENT_SIZES
        }

    val outputValue: Int
        get() = outputValueDigits.fold(0) { acc, digit ->
            (10 * acc) + valueOf(digit)
        }

    private val patternsByDigit: Map<Int, Set<Char>> by lazy {
        DIGITS.associateWith { digit ->
            val segmentSize = SEGMENT_SIZES_BY_DIGIT[digit]
            val pattern = signalPatterns.find { it.length == segmentSize }
            pattern?.toSet() ?: emptySet()
        }
    }

    private fun valueOf(digit: String): Int {
        return when (val segmentSize = digit.length) {
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
            else -> DIGITS_BY_DISTINCT_SEGMENT_SIZE[segmentSize] ?: throw IllegalArgumentException()
        }
    }

    private fun String.intersectingChars(digit: Int): Int {
        val pattern = patternsByDigit.getValue(digit)
        return toSet().intersect(pattern).size
    }

    private companion object {

        private val SEGMENTS_BY_DIGIT = mapOf(
            0 to setOf('a', 'b', 'c', 'e', 'f', 'g'),
            1 to setOf('c', 'f'),
            2 to setOf('a', 'c', 'd', 'e', 'g'),
            3 to setOf('a', 'c', 'd', 'f', 'g'),
            4 to setOf('b', 'c', 'd', 'f'),
            5 to setOf('a', 'b', 'd', 'f', 'g'),
            6 to setOf('a', 'b', 'd', 'e', 'f', 'g'),
            7 to setOf('a', 'c', 'f'),
            8 to setOf('a', 'b', 'c', 'd', 'e', 'f', 'g'),
            9 to setOf('a', 'b', 'c', 'd', 'f', 'g')
        )

        private val SEGMENT_SIZES_BY_DIGIT = DIGITS.associateWith { digit ->
            SEGMENTS_BY_DIGIT.getValue(digit).size
        }

        private val DISTINCT_SEGMENT_SIZES_BY_DIGIT = SEGMENT_SIZES_BY_DIGIT.filter { a ->
            SEGMENT_SIZES_BY_DIGIT.count { b ->
                a.value == b.value
            } == 1
        }

        private fun <K, V> Map<K, V>.reversed(): Map<V, K> {
            return entries.associateBy(Map.Entry<K, V>::value, Map.Entry<K, V>::key)
        }

        private val DISTINCT_SEGMENT_SIZES = DISTINCT_SEGMENT_SIZES_BY_DIGIT.values

        private val DIGITS_BY_DISTINCT_SEGMENT_SIZE = DISTINCT_SEGMENT_SIZES_BY_DIGIT.reversed()
    }
}
