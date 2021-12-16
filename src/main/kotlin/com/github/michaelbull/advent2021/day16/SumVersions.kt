package com.github.michaelbull.advent2021.day16

fun sumVersions(packet: Packet): Int {
    return when (packet) {
        is LiteralPacket -> packet.version
        is OperatorPacket -> packet.version + packet.subpackets.sumOf(::sumVersions)
    }
}
