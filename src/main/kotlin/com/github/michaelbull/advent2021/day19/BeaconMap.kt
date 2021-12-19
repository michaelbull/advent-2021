package com.github.michaelbull.advent2021.day19

import com.github.michaelbull.advent2021.math.Vector3

fun Sequence<String>.toBeaconMap(): BeaconMap {
    val it = iterator()
    val scanners = generateSequence(it::readScanner).toList()
    return BeaconMap(scanners)
}

data class BeaconMap(
    val scanners: List<Scanner>
) {

    fun count(): Int {
        return scan().beacons.size
    }

    fun maxDistance(): Int {
        val translations = scan().translations

        return translations.maxOf { a ->
            translations.maxOf { b -> a manhattanDistanceTo b }
        }
    }

    fun scan(): ScanResult {
        val beacons = scanners.take(1).flatMap(Scanner::beacons).toMutableSet()
        val remaining = scanners.drop(1).toMutableList()
        val translations = mutableSetOf<Vector3>()

        while (remaining.isNotEmpty()) {
            for (i in remaining.indices.reversed()) {
                val (translation, translatedBeacons) = scan(beacons, remaining[i]) ?: continue

                remaining[i] = remaining.last()
                remaining.removeLast()

                beacons += translatedBeacons
                translations += translation
            }
        }

        return ScanResult(beacons, translations)
    }

    private fun scan(knownBeacons: Set<Vector3>, scanner: Scanner): Pair<Vector3, List<Vector3>>? {
        for (rotation in ROTATION_RANGE) {
            val rotatedBeacons = scanner.beacons.map { it.rotateBy(rotation) }

            val translations = knownBeacons
                .product(rotatedBeacons)
                .map { (a, b) -> a - b }

            for (translation in translations) {
                val translatedBeacons = rotatedBeacons.map { it + translation }
                val overlaps = translatedBeacons.count(knownBeacons::contains)

                if (overlaps >= 12) {
                    return translation to translatedBeacons
                }
            }
        }

        return null
    }

    private fun <T, R> Iterable<T>.product(other: Iterable<R>): List<Pair<T, R>> {
        return flatMap { left ->
            other.map { right -> left to right }
        }
    }

    private infix fun Vector3.manhattanDistanceTo(other: Vector3): Int {
        val (deltaX, deltaY, deltaZ) = (other - this).abs()
        return deltaX + deltaY + deltaZ
    }

    private fun Vector3.rotateBy(rotation: Int): Vector3 {
        return when (rotation) {
            0 -> Vector3(x, y, z)
            1 -> Vector3(x, z, -y)
            2 -> Vector3(x, -y, -z)
            3 -> Vector3(x, -z, y)
            4 -> Vector3(y, x, -z)
            5 -> Vector3(y, z, x)
            6 -> Vector3(y, -x, z)
            7 -> Vector3(y, -z, -x)
            8 -> Vector3(z, x, y)
            9 -> Vector3(z, y, -x)
            10 -> Vector3(z, -x, -y)
            11 -> Vector3(z, -y, x)
            12 -> Vector3(-x, y, -z)
            13 -> Vector3(-x, z, y)
            14 -> Vector3(-x, -y, z)
            15 -> Vector3(-x, -z, -y)
            16 -> Vector3(-y, x, z)
            17 -> Vector3(-y, z, -x)
            18 -> Vector3(-y, -x, -z)
            19 -> Vector3(-y, -z, x)
            20 -> Vector3(-z, x, -y)
            21 -> Vector3(-z, y, x)
            22 -> Vector3(-z, -x, y)
            23 -> Vector3(-z, -y, -x)
            else -> throw IllegalArgumentException("rotation must be in [$ROTATION_RANGE], but was: $rotation")
        }
    }

    private companion object {
        private val ROTATION_RANGE = 0..23
    }
}
