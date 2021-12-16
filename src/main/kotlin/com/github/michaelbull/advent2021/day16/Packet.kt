package com.github.michaelbull.advent2021.day16

sealed interface Packet {
    val version: Int
    val trailing: String
}

data class LiteralPacket(
    override val version: Int,
    override val trailing: String,
    val value: Long
) : Packet

data class OperatorPacket(
    override val version: Int,
    override val trailing: String,
    val type: OperatorType,
    val subpackets: List<Packet> = emptyList()
) : Packet

fun sumVersion(packet: Packet): Int {
    return when (packet) {
        is LiteralPacket -> packet.version
        is OperatorPacket -> packet.version + packet.subpackets.sumOf(::sumVersion)
    }
}

private fun Long.greaterThan(other: Long): Long {
    return if (this > other) 1 else 0
}

private fun Long.lessThan(other: Long): Long {
    return if (this < other) 1 else 0
}

private fun Long.equalTo(other: Long): Long {
    return if (this == other) 1 else 0
}

fun evaluate(packet: Packet): Long {
    return when (packet) {
        is LiteralPacket -> packet.value
        is OperatorPacket -> {
            val operation: ((Long, Long) -> Long) = when (packet.type) {
                OperatorType.SUM -> Long::plus
                OperatorType.PRODUCT -> Long::times
                OperatorType.MINIMUM -> Math::min
                OperatorType.MAXIMUM -> Math::max
                OperatorType.GREATER_THAN -> Long::greaterThan
                OperatorType.LESS_THAN -> Long::lessThan
                OperatorType.EQUAL_TO -> Long::equalTo
            }

            packet.subpackets
                .map(::evaluate)
                .reduce(operation)
        }
    }
}
