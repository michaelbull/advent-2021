package com.github.michaelbull.advent2021.day18

operator fun SnailfishNumber.plus(other: SnailfishNumber): SnailfishNumber {
    return (this to other).reduce()
}

fun SnailfishNumber.reduce(): SnailfishNumber {
    return explodeReducing() ?: splitReducing() ?: this
}

private fun SnailfishNumber.explodeReducing(): SnailfishNumber? {
    return explode()?.number?.reduce()
}

private fun SnailfishNumber.splitReducing(): SnailfishNumber? {
    return split()?.let(SnailfishNumber::reduce)
}
