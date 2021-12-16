package com.github.michaelbull.advent2021.day16

import com.github.michaelbull.advent2021.Puzzle


object Day16 : Puzzle<Packet, Any>(day = 16) {

    override fun parse(input: Sequence<String>): Packet {
        val decoded = input.first().chunked(2).joinToString("") {
            it.toUByte(16).toString(2).padStart(8, '0')
        }

        return PacketReader(decoded).read()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(packet: Packet): Int {
        return sumVersion(packet)
    }

    fun part2(packet: Packet): Long {
        return evaluate(packet)
    }

}
