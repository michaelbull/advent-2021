package com.github.michaelbull.advent2021.math

import kotlin.math.abs

data class Vector2Range(
    override val start: Vector2,
    override val endInclusive: Vector2
) : Iterable<Vector2>, ClosedRange<Vector2> {

    val isHorizontal: Boolean
        get() = start.x == endInclusive.x

    val isVertical: Boolean
        get() = start.y == endInclusive.y

    val isDiagonal: Boolean
        get() = abs(xDelta) == abs(yDelta)

    val xRange: IntRange
        get() = start.x..endInclusive.x

    val yRange: IntRange
        get() = start.y..endInclusive.y

    val xDelta: Int
        get() = endInclusive.x - start.x

    val yDelta: Int
        get() = endInclusive.y - start.y

    override fun iterator(): Iterator<Vector2> {
        return Vector2Iterator()
    }

    override fun contains(value: Vector2): Boolean {
        return value.x in xRange && value.y in yRange
    }

    override fun isEmpty(): Boolean {
        return start.x > endInclusive.x || start.y > endInclusive.y
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return other is Vector2Range && (isEmpty() && other.isEmpty() ||
            start == other.start && endInclusive == other.endInclusive)
    }

    override fun hashCode(): Int {
        return if (isEmpty()) -1 else 31 * start.hashCode() + endInclusive.hashCode()
    }

    override fun toString(): String {
        return "$start..$endInclusive"
    }

    private inner class Vector2Iterator : Iterator<Vector2> {

        private var current: Vector2? = null

        override fun hasNext(): Boolean {
            val current = current

            return when {
                current == null -> start.x < endInclusive.x && start.y < endInclusive.y
                current.x < endInclusive.x && current.y <= endInclusive.y -> true
                current.x == endInclusive.x && current.y < endInclusive.y -> true
                else -> false
            }
        }

        override fun next(): Vector2 {
            var next = current

            next = when {
                next != null -> next.step()
                start.x < endInclusive.x && start.y < endInclusive.y -> start
                else -> throw NoSuchElementException()
            }

            current = next
            return next
        }

        private fun Vector2.step(): Vector2 {
            return when {
                x < endInclusive.x && y <= endInclusive.y -> copy(
                    x = x + 1
                )

                x == endInclusive.x && y < endInclusive.y -> copy(
                    x = start.x,
                    y = y + 1
                )

                else -> throw NoSuchElementException()
            }
        }
    }
}
