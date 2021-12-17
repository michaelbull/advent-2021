package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2.Companion.ZERO
import com.github.michaelbull.advent2021.math.Vector2Range
import com.github.michaelbull.advent2021.math.triangular

class ProbeLauncher(
    private val targetArea: Vector2Range
) {

    private val xRange = targetArea.xRange
    private val yRange = targetArea.yRange

    val maxLandingY: Int?
        get() = landingInitialVelocities().map(::triangularY).maxOrNull()

    val landingInitialVelocityCount: Int
        get() = landingInitialVelocities().count()

    private fun landingInitialVelocities() = sequence {
        val minX = generateSequence(0) { it + 1 }.first { triangular(it) >= xRange.first }

        for (x in minX..xRange.last) {
            for (y in yRange.first until -yRange.first) {
                val initialVelocity = Vector2(x, y)
                val probe = Probe(ZERO, initialVelocity)

                if (probe landsIn targetArea) {
                    yield(initialVelocity)
                }
            }
        }
    }

    private fun triangularY(position: Vector2): Int {
        return triangular(position.y)
    }
}
