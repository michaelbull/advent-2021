package com.github.michaelbull.advent2021.day14

fun Sequence<String>.toPolymerManual(): PolymerManual {
    val it = iterator()
    val template = it.next()
    val insertions = it.toInsertionMap()
    return PolymerManual(template.toPolymer(), insertions)
}

data class PolymerManual(
    val template: Polymer,
    val insertions: InsertionMap
) {
    fun create(steps: Int): Polymer {
        return template.stepSequence(insertions).elementAt(steps)
    }
}
