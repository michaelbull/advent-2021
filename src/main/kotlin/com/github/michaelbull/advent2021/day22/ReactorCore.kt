package com.github.michaelbull.advent2021.day22

import com.github.michaelbull.advent2021.math.Vector3
import com.github.michaelbull.advent2021.math.Vector3Range

data class ReactorCore(
    val enabledCubes: Set<Vector3> = emptySet()
) {

    val enabledCount: Int
        get() = enabledCubes.size

    fun apply(steps: Sequence<RebootStep>, region: Vector3Range): ReactorCore {
        val enabled = enabledCubes.toMutableSet()

        for (step in steps) {
            val range = step.cuboid.coerceIn(region)

            for (position in range) {
                when (step) {
                    is RebootStep.On -> enabled += position
                    is RebootStep.Off -> enabled -= position
                }
            }
        }

        return copy(enabledCubes = enabled)
    }
}
