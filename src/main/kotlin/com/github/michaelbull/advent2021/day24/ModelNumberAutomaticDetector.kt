package com.github.michaelbull.advent2021.day24

import com.github.michaelbull.advent2021.day24.Instruction.Literal.Div
import com.github.michaelbull.advent2021.day24.VariableName.W
import com.github.michaelbull.advent2021.day24.VariableName.Z
import kotlin.math.max
import kotlin.math.pow

class ModelNumberAutomaticDetector(
    instructions: List<Instruction>
) {

    private val divLiteralMin = instructions
        .filterIsInstance<Div>()
        .minOf(Instruction.Literal::operand)

    private val divLiteralMax = instructions
        .filterIsInstance<Div>()
        .maxOf(Instruction.Literal::operand)

    private val divLiteralRange = divLiteralMin..divLiteralMax
    private val chunks = instructions.chunked(CHUNK_SIZE)
    private val zStackHeight = maxStackHeight(Z, instructions)

    private val maxZ = divLiteralRange.count()
        .toDouble()
        .pow(zStackHeight)
        .toInt()

    fun largest(): Long? {
        return detect(LARGEST_TO_SMALLEST)
    }

    fun smallest(): Long? {
        return detect(SMALLEST_TO_LARGEST)
    }

    private fun detect(
        digits: IntProgression,
        visited: MutableSet<DetectNode> = mutableSetOf(),
        current: DetectNode = DetectNode.START,
        acc: Long = 0
    ): Long? {
        if (current in visited || current.z > maxZ) {
            return null
        }

        visited += current

        val chunk = chunks[current.chunkIndex]
        val nextChunkIndex = current.chunkIndex + 1
        val chunksRemaining = nextChunkIndex < chunks.size

        for (digit in digits) {
            val alu = ArithmeticLogicUnit()

            alu[Z] = current.z

            alu.onInput {
                if (it == W) {
                    digit.toLong()
                } else {
                    throw IllegalArgumentException("no input for variable: $it")
                }
            }

            alu.execute(chunk)

            if (chunksRemaining) {
                val nextNode = DetectNode(nextChunkIndex, alu[Z])
                val next = detect(digits, visited, nextNode, (acc * 10) + digit)

                if (next != null) {
                    return next
                }
            } else if (alu.isValid()) {
                return (acc * 10) + digit
            }
        }

        return null
    }

    private fun maxStackHeight(variable: VariableName, instructions: List<Instruction>): Int {
        val (_, max) = instructions.fold(0 to 0) { (current, max), instruction ->
            val delta = when {
                instruction.isPush(variable) -> +1
                instruction.isPop(variable) -> -1
                else -> 0
            }

            val next = current + delta

            next to max(next, max)
        }

        return max
    }

    private fun Instruction.isPush(variable: VariableName): Boolean {
        return this is Div && this.variable == variable && operand == divLiteralMin
    }

    private fun Instruction.isPop(variable: VariableName): Boolean {
        return this is Div && this.variable == variable && operand == divLiteralMax
    }

    private data class DetectNode(
        val chunkIndex: Int,
        val z: Long
    ) {
        companion object {
            val START = DetectNode(0, 0)
        }
    }

    private companion object {
        private const val CHUNK_SIZE = 18
        private val LARGEST_TO_SMALLEST = 9 downTo 1
        private val SMALLEST_TO_LARGEST = 1..9
    }
}
