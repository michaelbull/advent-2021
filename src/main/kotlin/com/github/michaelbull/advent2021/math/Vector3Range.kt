package com.github.michaelbull.advent2021.math

data class Vector3Range(
    override val start: Vector3,
    override val endInclusive: Vector3
) : Iterable<Vector3>, ClosedRange<Vector3> {

    val xRange: IntRange
        get() = start.x..endInclusive.x

    val yRange: IntRange
        get() = start.y..endInclusive.y

    val zRange: IntRange
        get() = start.z..endInclusive.z

    val xDelta: Int
        get() = endInclusive.x - start.x

    val yDelta: Int
        get() = endInclusive.y - start.y

    val zDelta: Int
        get() = endInclusive.z - start.z

    fun coerceIn(range: Vector3Range): Vector3Range {
        val coercedStart = start.coerceAtLeast(range.start)
        val coercedEndInclusive = endInclusive.coerceAtMost(range.endInclusive)
        return coercedStart..coercedEndInclusive
    }

    override fun contains(value: Vector3): Boolean {
        return value.x in xRange && value.y in yRange && value.z in zRange
    }

    override fun isEmpty(): Boolean {
        return xDelta < 0 || yDelta < 0 || zDelta < 0
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun iterator(): Iterator<Vector3> {
        return Vector3Iterator()
    }

    private inner class Vector3Iterator : Iterator<Vector3> {

        private var current: Vector3? = null

        override fun hasNext(): Boolean {
            val current = current

            return when {
                current == null -> start.x < endInclusive.x && start.y < endInclusive.y && start.z < endInclusive.z
                current.x < endInclusive.x && current.y <= endInclusive.y && current.z <= endInclusive.z -> true
                current.x == endInclusive.x && current.y < endInclusive.y && current.z <= endInclusive.z -> true
                current.x == endInclusive.x && current.y == endInclusive.y && current.z < endInclusive.z -> true
                else -> false
            }
        }

        override fun next(): Vector3 {
            var next = current

            next = when {
                next != null -> next.step()
                start.x < endInclusive.x && start.y < endInclusive.y && start.z < endInclusive.z -> start
                else -> throw NoSuchElementException()
            }

            current = next
            return next
        }

        private fun Vector3.step(): Vector3 {
            return when {
                x < endInclusive.x && y <= endInclusive.y && z <= endInclusive.z -> copy(
                    x = x + 1
                )

                x == endInclusive.x && y < endInclusive.y && z <= endInclusive.z -> copy(
                    x = start.x,
                    y = y + 1
                )

                x == endInclusive.x && y == endInclusive.y && z < endInclusive.z -> copy(
                    x = start.x,
                    y = start.y,
                    z = z + 1
                )

                else -> throw NoSuchElementException()
            }
        }
    }
}
