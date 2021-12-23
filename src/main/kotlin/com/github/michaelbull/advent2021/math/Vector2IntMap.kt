package com.github.michaelbull.advent2021.math

class Vector2IntMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Int = { 0 }
) {

    val xRange = 0 until width
    val yRange = 0 until height

    private val values = IntArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Int) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Int) {
        values[indexOf(x ,y)] = value
    }

    operator fun get(x: Int, y: Int): Int {
        return values[indexOf(x, y)]
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

    private fun check(x: Int, y: Int) {
        require(x in xRange)
        require(y in yRange)
    }

    private fun indexOf(position: Vector2): Int {
        check(position)
        return (position.y * width) + position.x
    }

    private fun indexOf(x: Int, y: Int): Int {
        check(x, y)
        return (y * width) + x
    }

    fun copy(width: Int = this.width, height: Int = this.height): Vector2IntMap {
        return Vector2IntMap(width, height) { (x, y) ->
            if (x !in xRange || y !in yRange) {
                0
            } else {
                this[x, y]
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector2IntMap

        if (!values.contentEquals(other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }
}
