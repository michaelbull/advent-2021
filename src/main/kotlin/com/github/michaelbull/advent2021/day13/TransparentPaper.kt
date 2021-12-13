package com.github.michaelbull.advent2021.day13

import com.github.michaelbull.advent2021.math.Vector2

fun Sequence<String>.toTransparentPaper(): TransparentPaper {
    val dots = mutableSetOf<Vector2>()
    val instructions = mutableListOf<FoldInstruction>()
    var readingInstructions = false

    for (line in this) {
        if (readingInstructions) {
            instructions += line.toFoldInstruction()
        } else if (line.isEmpty()) {
            readingInstructions = true
        } else {
            dots += line.toDot()
        }
    }

    return TransparentPaper(dots, instructions)
}

data class TransparentPaper(
    val dots: Set<Vector2>,
    val instructions: List<FoldInstruction>
) {

    private val xMin: Int
        get() = dots.minOf(Vector2::x)

    private val yMin: Int
        get() = dots.minOf(Vector2::y)

    private val xMax: Int
        get() = dots.maxOf(Vector2::x)

    private val yMax: Int
        get() = dots.maxOf(Vector2::y)

    private val xRange: IntRange
        get() = xMin..xMax

    private val yRange: IntRange
        get() = yMin..yMax

    fun fold(): TransparentPaper? {
        val instruction = instructions.firstOrNull() ?: return null

        return copy(
            dots = dots.mapTo(mutableSetOf()) { instruction.fold(it) },
            instructions = instructions.drop(1)
        )
    }

    fun foldSequence(): Sequence<TransparentPaper> {
        return generateSequence(this, TransparentPaper::fold)
    }

    fun decode(): String {
        return yRange.joinToString(separator = "\n") { y ->
            xRange.joinToString(separator = "") { x ->
                Vector2(x, y).decode()
            }
        }
    }

    private fun FoldInstruction.fold(dot: Vector2): Vector2 {
        return when (axis) {
            Axis.X -> dot.copy(x = fold(dot.x, line))
            Axis.Y -> dot.copy(y = fold(dot.y, line))
        }
    }

    private fun fold(dot: Int, line: Int): Int {
        require(dot != line) { "dots must not appear on fold line $line" }

        return if (dot > line) {
            (2 * line) - dot
        } else {
            dot
        }
    }

    private fun Vector2.decode(): String {
        return if (this in dots) "#" else "."
    }
}
