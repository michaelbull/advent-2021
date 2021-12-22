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

    override fun contains(value: Vector2): Boolean {
        return value.x in xRange && value.y in yRange
    }

    override fun iterator(): Iterator<Vector2> {
        return Vector2Iterator()
    }

    override fun isEmpty(): Boolean {
        return xDelta < 0 || yDelta < 0
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
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
