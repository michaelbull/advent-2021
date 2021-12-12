package com.github.michaelbull.advent2021.day12

typealias PathRule = (Cave, List<Cave>) -> Boolean

fun visitSmallOnce(cave: Cave, visited: List<Cave>): Boolean {
    return cave.isBig || cave !in visited.filter(Cave::isSmall)
}

fun visitSmallTwice(cave: Cave, visited: List<Cave>): Boolean {
    return if (cave.isBig) {
        true
    } else {
        val smallCaves = visited.filter(Cave::isSmall) + cave

        val occurrences = smallCaves
            .groupingBy { it }
            .eachCount()
            .values

        occurrences.all { it <= 2 } && occurrences.count { it == 2 } <= 1
    }
}
