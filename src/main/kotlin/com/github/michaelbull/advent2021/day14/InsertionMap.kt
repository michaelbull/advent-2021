package com.github.michaelbull.advent2021.day14

private val REGEX = "([A-Z])([A-Z]) -> ([A-Z])".toRegex()

typealias InsertionMap = Map<ElementPair, Char>

fun Iterator<String>.toInsertionMap(): InsertionMap {
    require(next().isEmpty())

    return buildMap {
        while (hasNext()) {
            val line = next()

            val result = requireNotNull(REGEX.matchEntire(line)) {
                "insertion rule must match '$REGEX', but was: '$line'"
            }

            val (left, right, insertion) = result.destructured
            val elementPair = left.first() to right.first()

            set(elementPair, insertion.first())
        }
    }
}
