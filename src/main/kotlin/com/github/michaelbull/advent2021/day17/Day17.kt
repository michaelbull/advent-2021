package com.github.michaelbull.advent2021.day17

import com.github.michaelbull.advent2021.Puzzle
import com.github.michaelbull.advent2021.math.Vector2Range

object Day17 : Puzzle<Vector2Range, Int>(day = 17) {

    override fun parse(input: Sequence<String>): Vector2Range {
        return input.single().toTargetArea()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(targetArea: Vector2Range): Int {
        return ProbeLauncher(targetArea).maxLandingY ?: error("No initial velocities land in $targetArea")
    }

    fun part2(targetArea: Vector2Range): Int {
        return ProbeLauncher(targetArea).landingInitialVelocityCount
    }
}
