package com.github.michaelbull.advent2021.day22

import com.github.michaelbull.advent2021.math.Vector3
import com.github.michaelbull.advent2021.math.Vector3Range

object ReactorCore {

    fun apply(steps: Sequence<RebootStep>, region: Vector3Range): Int {
        val enabled = mutableSetOf<Vector3>()

        for (step in steps) {
            val range = step.cuboid.coerceIn(region)

            for (position in range) {
                when (step) {
                    is RebootStep.On -> enabled += position
                    is RebootStep.Off -> enabled -= position
                }
            }
        }

        return enabled.size
    }

    fun apply(steps: Sequence<RebootStep>): Long {
        val allCounts = mutableMapOf<Vector3Range, Int>().withDefault { 0 }

        for (step in steps) {
            val stepCounts = mutableMapOf<Vector3Range, Int>().withDefault { 0 }

            for ((cuboid, count) in allCounts) {
                val intersection = step.cuboid.coerceIn(cuboid)

                if (intersection.isNotEmpty()) {
                    val stepCount = stepCounts.getValue(intersection)
                    stepCounts[intersection] = stepCount - count
                }
            }

            if (step is RebootStep.On) {
                val stepCount = stepCounts.getValue(step.cuboid)
                stepCounts[step.cuboid] = stepCount + 1
            }

            for ((stepCuboid, stepCount) in stepCounts) {
                val allCount = allCounts.getValue(stepCuboid)
                allCounts[stepCuboid] = allCount + stepCount
            }
        }

        return allCounts.entries.sumOf { (cube, count) ->
            cube.volume() * count
        }
    }

    private fun Vector3Range.volume(): Long {
        return (xDelta + 1L) * (yDelta + 1L) * (zDelta + 1L)
    }
}
