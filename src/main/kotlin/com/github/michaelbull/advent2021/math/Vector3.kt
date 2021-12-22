package com.github.michaelbull.advent2021.math

import kotlin.math.abs

fun Triple<Int, Int, Int>.toVector3(): Vector3 {
    return Vector3(this)
}

data class Vector3(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0
) : Comparable<Vector3> {

    constructor(triple: Triple<Int, Int, Int>) : this(triple.first, triple.second, triple.third)

    operator fun plus(amount: Int): Vector3 {
        return copy(
            x = this.x + amount,
            y = this.y + amount,
            z = this.z + amount
        )
    }

    operator fun plus(other: Vector3): Vector3 {
        return copy(
            x = this.x + other.x,
            y = this.y + other.y,
            z = this.z + other.z
        )
    }

    operator fun minus(amount: Int): Vector3 {
        return copy(
            x = this.x - amount,
            y = this.y - amount,
            z = this.z - amount
        )
    }

    operator fun minus(other: Vector3): Vector3 {
        return copy(
            x = this.x - other.x,
            y = this.y - other.y,
            z = this.z - other.z
        )
    }

    operator fun times(amount: Int): Vector3 {
        return copy(
            x = this.x * amount,
            y = this.y * amount,
            z = this.z * amount
        )
    }

    operator fun times(other: Vector3): Vector3 {
        return copy(
            x = this.x * other.x,
            y = this.y * other.y,
            z = this.z * other.z
        )
    }

    operator fun div(amount: Int): Vector3 {
        return copy(
            x = this.x / amount,
            y = this.y / amount,
            z = this.z / amount
        )
    }

    operator fun div(other: Vector3): Vector3 {
        return copy(
            x = this.x / other.x,
            y = this.y / other.y,
            z = this.z / other.z
        )
    }

    fun abs(): Vector3 {
        return copy(
            x = abs(this.x),
            y = abs(this.y),
            z = abs(this.z)
        )
    }

    fun coerceAtLeast(minimumValue: Vector3): Vector3 {
        return copy(
            x = x.coerceAtLeast(minimumValue.x),
            y = y.coerceAtLeast(minimumValue.y),
            z = z.coerceAtLeast(minimumValue.z),
        )
    }

    fun coerceAtMost(maximumValue: Vector3): Vector3 {
        return copy(
            x = x.coerceAtMost(maximumValue.x),
            y = y.coerceAtMost(maximumValue.y),
            z = z.coerceAtMost(maximumValue.z),
        )
    }

    operator fun rangeTo(other: Vector3): Vector3Range {
        return Vector3Range(this, other)
    }

    override fun compareTo(other: Vector3): Int {
        return when {
            x < other.x -> -1
            x > other.x -> +1
            y < other.y -> -1
            y > other.y -> +1
            z < other.z -> -1
            z > other.z -> +1
            else -> 0
        }
    }

    override fun toString(): String {
        return "[$x, $y, $z]"
    }

    companion object {
        val ZERO = Vector3(0, 0, 0)
    }
}
