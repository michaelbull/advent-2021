package com.github.michaelbull.advent2021.math

import java.util.BitSet

class Vector2BooleanMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Boolean = { false }
) {

    val xRange = 0 until width
    val yRange = 0 until height

    private val values = BitSet(width * height)

    init {
        for (x in xRange) {
            for (y in yRange) {
                val position = Vector2(x, y)
                set(position, init(position))
            }
        }
    }

    operator fun set(position: Vector2, value: Boolean) {
        values[indexOf(position)] = value
    }

    operator fun get(position: Vector2): Boolean {
        return values[indexOf(position)]
    }

    fun getOrNull(position: Vector2): Boolean? {
        return if (position in this) {
            values[indexOf(position)]
        } else {
            null
        }
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    private fun check(position: Vector2) {
        require(position.x in xRange)
        require(position.y in yRange)
    }

    private fun indexOf(position: Vector2): Int {
        check(position)
        return (position.y * width) + position.x
    }
}
