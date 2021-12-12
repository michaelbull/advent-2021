package com.github.michaelbull.advent2021.day12

fun Sequence<CaveConnection>.toCaveMap(): CaveMap {
    val connections = mutableMapOf<Cave, Set<Cave>>()

    for ((start, end) in this@toCaveMap) {
        connections[start] = connections.getOrDefault(start, emptySet()) + end
        connections[end] = connections.getOrDefault(end, emptySet()) + start
    }

    return CaveMap(connections)
}

data class CaveMap(
    val connections: Map<Cave, Set<Cave>>
) {

    fun countPaths(rule: PathRule): Int {
        return countPaths(Cave.START, emptyList(), rule)
    }

    private fun countPaths(cave: Cave, visited: List<Cave>, rule: PathRule): Int {
        return connections.getValue(cave).sumOf {
            if (it == Cave.END) {
                1
            } else if (it == Cave.START || !rule(it, visited)) {
                0
            } else {
                countPaths(it, visited + it, rule)
            }
        }
    }
}
