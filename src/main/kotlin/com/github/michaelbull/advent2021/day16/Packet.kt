package com.github.michaelbull.advent2021.day16

sealed interface Packet {
    val version: Int
}

data class LiteralPacket(
    override val version: Int,
    val value: Long
) : Packet {
    companion object {
        const val TYPE_ID = 4
    }
}

data class OperatorPacket(
    override val version: Int,
    val type: OperatorType,
    val subpackets: List<Packet> = emptyList()
) : Packet

fun BooleanIterator.nextPacket(): Packet {
    val version = nextInt(3)
    val typeId = nextInt(3)

    return if (typeId == LiteralPacket.TYPE_ID) {
        nextLiteralPacket(version)
    } else {
        val operatorType = OPERATOR_TYPES_BY_ID[typeId] ?: error("Unknown packet type: $typeId")

        val children = if (nextBoolean()) {
            takePackets(nextInt(11))
        } else {
            takeChildPackets(nextInt(15))
        }

        OperatorPacket(version, operatorType, children)
    }
}

fun BooleanIterator.nextLiteralPacket(version: Int): LiteralPacket {
    var value = 0L

    do {
        val reading = nextBoolean()
        val part = nextInt(4)
        value = (value shl 4) or part.toLong()
    } while (reading)

    return LiteralPacket(version, value)
}

fun BooleanIterator.takePackets(n: Int): List<Packet> {
    return List(n) { nextPacket() }
}

fun BooleanIterator.takeChildPackets(n: Int): List<Packet> {
    val children = take(n)

    return generateSequence { children }
        .takeWhile(BooleanIterator::hasNext)
        .map(BooleanIterator::nextPacket)
        .toList()
}

private fun BooleanIterator.take(n: Int): BooleanIterator = object : BooleanIterator() {
    private var remaining = n

    override fun hasNext(): Boolean {
        return remaining > 0 && this@take.hasNext()
    }

    override fun nextBoolean(): Boolean {
        remaining--
        return this@take.nextBoolean()
    }
}

private fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}

private val INT_BIT_RANGE = 1..Int.SIZE_BITS

private fun BooleanIterator.nextInt(len: Int): Int {
    require(len in INT_BIT_RANGE) { "len must be in $INT_BIT_RANGE, but was: $len" }

    var result = 0
    repeat(len) {
        result = (result shl 1) or nextBoolean().toInt()
    }

    return result
}
