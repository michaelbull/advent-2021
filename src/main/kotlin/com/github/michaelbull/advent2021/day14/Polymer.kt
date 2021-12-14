package com.github.michaelbull.advent2021.day14

private fun <T, K> Grouping<T, K>.eachCountLong(): Map<K, Long> {
    return fold(0L) { count, _ -> count + 1 }
}

fun String.toPolymer(): Polymer {
    return Polymer(
        elements = groupingBy { it }.eachCountLong(),
        pairs = zipWithNext().groupingBy { it }.eachCountLong()
    )
}

data class Polymer(
    val elements: Map<Char, Long>,
    val pairs: Map<ElementPair, Long>
) {

    val leastCommonQuantity: Long
        get() = elements.minOf(Map.Entry<Char, Long>::value)

    val mostCommonQuantity: Long
        get() = elements.maxOf(Map.Entry<Char, Long>::value)

    fun step(insertions: InsertionMap): Polymer {
        val nextElements = elements.toMutableMap()
        val nextPairs = mutableMapOf<Pair<Char, Char>, Long>()

        for ((pair, count) in pairs) {
            val insertion = insertions[pair] ?: continue
            nextPairs.increment(pair.first to insertion, count)
            nextPairs.increment(insertion to pair.second, count)
            nextElements.increment(insertion, count)
        }

        return Polymer(nextElements, nextPairs)
    }

    fun stepSequence(insertions: InsertionMap): Sequence<Polymer> {
        return generateSequence(this) { polymer ->
            polymer.step(insertions)
        }
    }

    private fun <K> MutableMap<K, Long>.increment(key: K, amount: Long) {
        val value = getOrDefault(key, 0)
        return set(key, value + amount)
    }
}
