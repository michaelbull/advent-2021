package com.github.michaelbull.advent2021.day15

import com.github.michaelbull.advent2021.math.Vector2
import java.util.PriorityQueue

private val RISK_RANGE = 1..9

fun Sequence<String>.toChitonCave(): ChitonCave {
    val riskLevels = buildMap {
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

    return ChitonCave(riskLevels)
}

data class ChitonCave(
    val riskLevels: Map<Vector2, Int>
) {

    val xMin: Int
        get() = riskLevels.keys.minOf(Vector2::x)

    val yMin: Int
        get() = riskLevels.keys.minOf(Vector2::y)

    val xMax: Int
        get() = riskLevels.keys.maxOf(Vector2::x)

    val yMax: Int
        get() = riskLevels.keys.maxOf(Vector2::y)

    val topLeft: Vector2
        get() = Vector2(xMin, yMin)

    val bottomRight: Vector2
        get() = Vector2(xMax, yMax)

    fun lowestTotalRisk(from: Vector2, to: Vector2): Int {
        val lowestRiskLevels = mutableMapOf(from to 0)
        val queue = PriorityQueue(compareBy(lowestRiskLevels::get))

        queue += from

        while (queue.isNotEmpty()) {
            val position = queue.poll()
            val lowestRiskLevel = lowestRiskLevels.getValue(position)

            for ((adjacentPosition, adjacentRiskLevel) in adjacentRiskLevels(position)) {
                val lowestAdjacentRiskLevel = lowestRiskLevels[adjacentPosition]
                val cumulativeRiskLevel = lowestRiskLevel + adjacentRiskLevel

                if (lowestAdjacentRiskLevel == null || cumulativeRiskLevel < lowestAdjacentRiskLevel) {
                    lowestRiskLevels[adjacentPosition] = cumulativeRiskLevel
                    queue += adjacentPosition
                }
            }
        }

        return lowestRiskLevels.getValue(to)
    }

    fun expand(times: Int): ChitonCave {
        val range = 0 until times
        val width = (xMax - xMin) + 1
        val height = (yMax - yMin) + 1

        val expandedRiskLevels = range.flatMap { x ->
            range.flatMap { y ->
                riskLevels.map { (position, riskLevel) ->
                    val expandedPosition = position + Vector2(x * width, y * height)
                    val expandedRiskLevel = ((riskLevel - 1 + x + y) % RISK_RANGE.last) + RISK_RANGE.first
                    expandedPosition to expandedRiskLevel
                }
            }
        }.toMap()

        return copy(riskLevels = expandedRiskLevels)
    }

    private fun adjacentRiskLevels(position: Vector2): Map<Vector2, Int> {
        return ADJACENT_OFFSETS
            .map { position + it }
            .filter { it in riskLevels }
            .associateWith(riskLevels::getValue)
    }

    private companion object {
        private val ADJACENT_OFFSETS = setOf(
            +0 to +1, /* north */
            +1 to +0, /* east */
            +0 to -1, /* south */
            -1 to +0, /* west */
        ).map(::Vector2)
    }
}
