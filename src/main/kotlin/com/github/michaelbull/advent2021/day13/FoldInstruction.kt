package com.github.michaelbull.advent2021.day13

private val REGEX = "fold along ($AXES_REGEX)=(\\d+)".toRegex()

fun String.toFoldInstruction(): FoldInstruction {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "fold instruction must match '$REGEX', but was: '$this'"
    }

    val (axis, line) = match.destructured

    return FoldInstruction(
        axis = axis.toAxis(),
        line = line.toInt()
    )
}

data class FoldInstruction(
    val axis: Axis,
    val line: Int
)
