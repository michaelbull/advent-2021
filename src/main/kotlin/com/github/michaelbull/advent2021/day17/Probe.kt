package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2021.math.Vector2.Companion.SOUTH_EAST
import com.github.michaelbull.advent2021.math.Vector2.Companion.SOUTH_WEST
import com.github.michaelbull.advent2021.math.Vector2Range

data class Probe(
    val position: Vector2,
    val velocity: Vector2
) {

    fun step(): Probe {
        return copy(
            position = position + velocity,
            velocity = velocity + drag()
        )
    }

    fun stepSequence(): Sequence<Probe> {
        return generateSequence(this, Probe::step)
    }

    infix fun landsIn(targetArea: Vector2Range): Boolean {
        val lastX = targetArea.xRange.last
        val firstY = targetArea.yRange.first

        return stepSequence()
            .takeWhile { it.position.x <= lastX && it.position.y >= firstY }
            .any { it.position in targetArea }
    }

    private fun drag(): Vector2 {
        return when {
            velocity.x < 0 -> SOUTH_EAST
            velocity.x > 0 -> SOUTH_WEST
            else -> SOUTH
        }
    }
}
