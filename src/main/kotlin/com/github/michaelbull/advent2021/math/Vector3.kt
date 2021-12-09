package com.github.michaelbull.advent2021.math

fun Triple<Int, Int, Int>.toVector3(): Vector3 {
    return Vector3(this)
}

data class Vector3(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0
) {

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

    companion object {
        val ZERO = Vector3(0, 0, 0)
    }
}
