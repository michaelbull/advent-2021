package com.github.michaelbull.advent2021.math

class Vector2IntMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Int = { 0 }
) {

    private val xRange = 0 until width
    private val yRange = 0 until height

    private val values = IntArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Int) {
        values[indexOf(position)] = value
    }

    operator fun get(position: Vector2): Int {
        return values[indexOf(position)]
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
