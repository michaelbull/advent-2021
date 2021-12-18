package com.github.michaelbull.advent2021.day18

import java.io.StringReader

fun String.toSnailfishNumber(): SnailfishNumber {
    return StringReader(this).use(::readSnailfishNumber)
}

fun Int.toSnailfishNumber(): SnailfishNumber {
    return RegularSnailfishNumber(this)
}

sealed interface SnailfishNumber {
    val magnitude: Int

    infix fun to(that: SnailfishNumber) = PairedSnailfishNumber(this, that)
}

data class RegularSnailfishNumber(
    override val magnitude: Int
) : SnailfishNumber

data class PairedSnailfishNumber(
    val left: SnailfishNumber,
    val right: SnailfishNumber
) : SnailfishNumber {

    override val magnitude: Int
        get() = (3 * left.magnitude) + (2 * right.magnitude)
}

private const val PAIR_OPENER = '['
private const val PAIR_CLOSER = ']'
private const val PAIR_SEPARATOR = ','

private fun readSnailfishNumber(reader: StringReader): SnailfishNumber {
    val head = reader.read()
    require(head != -1) { "unexpected eof" }

    return if (head.toChar() == PAIR_OPENER) {
        readPair(reader)
    } else {
        RegularSnailfishNumber(head.toChar().digitToInt())
    }
}

private fun readPair(reader: StringReader): PairedSnailfishNumber {
    val left = readSnailfishNumber(reader)

    val separator = reader.read().toChar()
    require(separator == PAIR_SEPARATOR) { "expected '$PAIR_SEPARATOR', but was: $separator" }

    val right = readSnailfishNumber(reader)

    val closer = reader.read().toChar()
    require(closer == PAIR_CLOSER) { "expected '$PAIR_CLOSER', but was: $closer" }

    return PairedSnailfishNumber(left, right)
}
