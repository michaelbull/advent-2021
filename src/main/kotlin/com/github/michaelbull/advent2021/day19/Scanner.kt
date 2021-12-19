package com.github.michaelbull.advent2021.day19

import com.github.michaelbull.advent2021.math.Vector3

private val COORDINATE_REGEX = "(-?\\d+)".toRegex()
private val HEADER_REGEX = "--- scanner (\\d+) ---".toRegex()
private val BEACON_REGEX = "$COORDINATE_REGEX,$COORDINATE_REGEX,$COORDINATE_REGEX".toRegex()

fun Iterator<String>.readScanner(): Scanner? {
    val number = readNumber() ?: return null
    val beacons = generateSequence(::readBeacon)
    return Scanner(number, beacons.toList())
}

private fun Iterator<String>.readNumber(): Int? {
    return if (hasNext()) {
        val line = next()

        val result = requireNotNull(HEADER_REGEX.matchEntire(line)) {
            "scanner must start with line matching '$HEADER_REGEX', but was: $line"
        }

        return result.groupValues[1].toInt()
    } else {
        null
    }
}

private fun Iterator<String>.readBeacon(): Vector3? {
    return if (hasNext()) {
        val line = next()
        if (line.isEmpty()) {
            return null
        }

        val result = requireNotNull(BEACON_REGEX.matchEntire(line)) {
            "beacons must match '$BEACON_REGEX', but was: $line"
        }

        val (x, y, z) = result.groupValues.drop(1).map(String::toInt)
        Vector3(x, y, z)
    } else {
        null
    }
}

data class Scanner(
    val number: Int,
    val beacons: List<Vector3>
)
