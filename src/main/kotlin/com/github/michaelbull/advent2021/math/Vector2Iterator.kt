package com.github.michaelbull.advent2021.math

class Vector2Iterator(
    private val first: Vector2,
    private val last: Vector2,
    private val step: Vector2
) : Iterator<Vector2> {

    private var current: Vector2? = null

    override fun hasNext(): Boolean {
        return current == null || current != last
    }

    override fun next(): Vector2 {
        val next = current?.let { it + step } ?: first
        current = next
        return next
    }
}
