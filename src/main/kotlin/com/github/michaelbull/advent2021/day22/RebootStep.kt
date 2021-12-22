package com.github.michaelbull.advent2021.day22

import com.github.michaelbull.advent2021.math.Vector3
import com.github.michaelbull.advent2021.math.Vector3Range

private val COORDINATE_REGEX = """(-?\d+)""".toRegex()
private val RANGE_REGEX = """$COORDINATE_REGEX\.\.$COORDINATE_REGEX""".toRegex()
private val REGEX = "(on|off) x=$RANGE_REGEX,y=$RANGE_REGEX,z=$RANGE_REGEX".toRegex()

fun String.toRebootStep(): RebootStep {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "reboot step must match '$REGEX', but was: '$this'"
    }

    val (type, xMin, xMax, yMin, yMax, zMin, zMax) = result.destructured
    val start = Vector3(xMin.toInt(), yMin.toInt(), zMin.toInt())
    val endInclusive = Vector3(xMax.toInt(), yMax.toInt(), zMax.toInt())
    val cuboid = start..endInclusive

    return when (type) {
        "on" -> RebootStep.On(cuboid)
        "off" -> RebootStep.Off(cuboid)
        else -> throw IllegalArgumentException("type must be on or off, but was: '$type'")
    }
}

sealed interface RebootStep {
    val cuboid: Vector3Range

    data class On(
        override val cuboid: Vector3Range
    ) : RebootStep

    data class Off(
        override val cuboid: Vector3Range
    ) : RebootStep
}

