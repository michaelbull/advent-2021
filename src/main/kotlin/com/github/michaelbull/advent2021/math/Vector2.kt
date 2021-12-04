package com.github.michaelbull.advent2021.math

data class Vector2(
    val x: Int = 0,
    val y: Int = 0
) {

    operator fun plus(amount: Int): Vector2 {
        return copy(
            x = this.x + amount,
            y = this.y + amount
        )
    }

    operator fun plus(other: Vector2): Vector2 {
        return copy(
            x = this.x + other.x,
            y = this.y + other.y
        )
    }

    operator fun minus(amount: Int): Vector2 {
        return copy(
            x = this.x - amount,
            y = this.y - amount
        )
    }

    operator fun minus(other: Vector2): Vector2 {
        return copy(
            x = this.x - other.x,
            y = this.y - other.y
        )
    }

    operator fun times(amount: Int): Vector2 {
        return copy(
            x = this.x * amount,
            y = this.y * amount
        )
    }

    operator fun times(other: Vector2): Vector2 {
        return copy(
            x = this.x * other.x,
            y = this.y * other.y
        )
    }

    operator fun div(amount: Int): Vector2 {
        return copy(
            x = this.x / amount,
            y = this.y / amount
        )
    }

    operator fun div(other: Vector2): Vector2 {
        return copy(
            x = this.x / other.x,
            y = this.y / other.y
        )
    }

    companion object {
        val ZERO = Vector2(0, 0)
    }
}
