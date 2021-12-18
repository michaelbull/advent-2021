package com.github.michaelbull.advent2021.day18

fun SnailfishNumber.split(): SnailfishNumber? {
    return when (this) {
        is RegularSnailfishNumber -> split()
        is PairedSnailfishNumber -> splitLeft() ?: splitRight()
    }
}

private fun PairedSnailfishNumber.splitLeft(): SnailfishNumber? {
    return left.split()?.let { it to right }
}

private fun PairedSnailfishNumber.splitRight(): SnailfishNumber? {
    return right.split()?.let { left to it }
}

private fun RegularSnailfishNumber.split(): SnailfishNumber? {
    return if (magnitude >= 10) {
        val left = (magnitude / 2).toSnailfishNumber()
        val right = ((magnitude + 1) / 2).toSnailfishNumber()
        left to right
    } else {
        null
    }
}
