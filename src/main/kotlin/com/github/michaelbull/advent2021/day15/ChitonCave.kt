package com.github.michaelbull.advent2021.day15

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2021.math.Vector2IntMap
import com.github.michaelbull.advent2021.math.rem
import java.util.PriorityQueue

private val RISK_RANGE = 1..9

fun Sequence<String>.toChitonCave(): ChitonCave {
    val map = buildMap {
        for ((y, line) in this@toChitonCave.withIndex()) {
            for ((x, char) in line.withIndex()) {
                val position = Vector2(x, y)
                val risk = char.digitToInt()

                require(risk in RISK_RANGE) {
                    "risk at $position must be in range $RISK_RANGE, but was: $risk"
                }

                set(position, risk)
            }
        }
    }

    val width = map.keys.maxOf(Vector2::x) + 1
    val height = map.keys.maxOf(Vector2::y) + 1
    val riskLevels = Vector2IntMap(width, height, map::getValue)

    return ChitonCave(riskLevels)
}

data class ChitonCave(
    val riskLevels: Vector2IntMap
) {

    val topLeft: Vector2
        get() = Vector2.ZERO

    val bottomRight: Vector2
        get() = Vector2(riskLevels.width - 1, riskLevels.height - 1)

    fun lowestTotalRisk(from: Vector2, to: Vector2): Int {
        val lowestRiskLevels = Vector2IntMap(riskLevels.width, riskLevels.height) {
            if (it == from) 0 else Int.MAX_VALUE
        }

        val queue = PriorityQueue(compareBy(lowestRiskLevels::get))

        queue += from

        while (queue.isNotEmpty()) {
            val position = queue.poll()
            val lowestRiskLevel = lowestRiskLevels[position]

            if (position == to) {
                return lowestRiskLevel
            }

            for ((adjacentPosition, adjacentRiskLevel) in adjacentRiskLevels(position)) {
                val lowestAdjacentRiskLevel = lowestRiskLevels[adjacentPosition]
                val cumulativeRiskLevel = lowestRiskLevel + adjacentRiskLevel

                if (cumulativeRiskLevel < lowestAdjacentRiskLevel) {
                    lowestRiskLevels[adjacentPosition] = cumulativeRiskLevel
                    queue += adjacentPosition
                }
            }
        }

        throw IllegalArgumentException()
    }

    fun expand(times: Int): ChitonCave {
        val width = riskLevels.width
        val height = riskLevels.height

        val expandedWidth = width * times
        val expandedHeight = height * times

        val expandedRiskLevels = Vector2IntMap(expandedWidth, expandedHeight) { position ->
            val base = position / Vector2(width, height)
            val local = position % Vector2(width, height)
            val riskLevel = riskLevels[local]
            (riskLevel + base.x + base.y) % RISK_RANGE
        }

        return copy(riskLevels = expandedRiskLevels)
    }

    private fun adjacentRiskLevels(position: Vector2): Map<Vector2, Int> {
        return CARDINAL_DIRECTIONS
            .map { position + it }
            .filter { it in riskLevels }
            .associateWith(riskLevels::get)
    }
}
