package com.github.michaelbull.advent2021.day18

data class Explosion(
    val number: SnailfishNumber,
    val remainingLeft: Int = 0,
    val remainingRight: Int = 0
)

fun SnailfishNumber.explode(depth: Int = 0): Explosion? {
    return when (this) {
        is RegularSnailfishNumber -> null
        is PairedSnailfishNumber -> {
            if (isBranch) {
                explodeLeft(depth) ?: explodeRight(depth)
            } else if (depth >= 4) {
                explode()
            } else {
                null
            }
        }
    }
}

private val PairedSnailfishNumber.isBranch: Boolean
    get() = left is PairedSnailfishNumber || right is PairedSnailfishNumber

private fun PairedSnailfishNumber.explode(): Explosion {
    return Explosion(0.toSnailfishNumber(), left.magnitude, right.magnitude)
}

private fun PairedSnailfishNumber.explodeLeft(depth: Int): Explosion? {
    return left.explode(depth + 1)?.let(::terminateRight)
}

private fun PairedSnailfishNumber.explodeRight(depth: Int): Explosion? {
    return right.explode(depth + 1)?.let(::terminateLeft)
}

private fun PairedSnailfishNumber.terminateLeft(explosion: Explosion): Explosion {
    return explosion.copy(
        number = left.addRight(explosion.remainingLeft) to explosion.number,
        remainingLeft = 0
    )
}

private fun PairedSnailfishNumber.terminateRight(explosion: Explosion): Explosion {
    return explosion.copy(
        number = explosion.number to right.addLeft(explosion.remainingRight),
        remainingRight = 0
    )
}

private fun SnailfishNumber.addLeft(amount: Int): SnailfishNumber {
    return if (amount == 0) {
        this
    } else when (this) {
        is RegularSnailfishNumber -> copy(magnitude = magnitude + amount)
        is PairedSnailfishNumber -> copy(left = left.addLeft(amount))
    }
}

private fun SnailfishNumber.addRight(amount: Int): SnailfishNumber {
    return if (amount == 0) {
        this
    } else when (this) {
        is RegularSnailfishNumber -> copy(magnitude = magnitude + amount)
        is PairedSnailfishNumber -> copy(right = right.addRight(amount))
    }
}
