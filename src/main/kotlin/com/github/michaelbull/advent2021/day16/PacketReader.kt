package com.github.michaelbull.advent2021.day16

class PacketReader(input: String) {

    private val remaining = ArrayDeque(input.toList())

    private val trailing: String
        get() = remaining.joinToString(separator = "")

    fun read(): Packet {
        val version = read(3).toInt(2)
        val typeId = read(3).toInt(2)

        return if (typeId == LITERAL_PACKET_TYPE_ID) {
            readLiteral(version)
        } else {
            val operatorType = OPERATOR_TYPES_BY_ID[typeId] ?: error("Unknown packet type: $typeId")
            val lengthTypeId = read(1).toInt(2)

            when (LENGTH_TYPES_BY_ID[lengthTypeId]) {
                LengthType.LENGTH -> readLength(version, operatorType)
                LengthType.COUNT -> readCount(version, operatorType)
                else -> error("Unknown length type: $lengthTypeId")
            }
        }
    }

    private fun readLiteral(version: Int): LiteralPacket {
        val value = buildString {
            while (true) {
                val remaining = read(5).padEnd(5, '0')
                append(remaining.substring(1))

                if (remaining.first() == '0') {
                    break
                }
            }
        }.toLong(2)

        return LiteralPacket(
            version,
            trailing,
            value
        )
    }

    private fun readCount(version: Int, type: OperatorType): OperatorPacket {
        val count = read(11).toInt(2)
        var content = trailing

        val subpackets = buildList {
            repeat(count) {
                val subpacket = PacketReader(content).read()
                add(subpacket)
                content = subpacket.trailing
            }
        }

        return OperatorPacket(
            version,
            content,
            type,
            subpackets
        )
    }

    private fun readLength(version: Int, type: OperatorType): OperatorPacket {
        val length = read(15).toInt(2)
        var content = read(length)

        val subpackets = buildList {
            while (content.isNotEmpty()) {
                val subpacket = PacketReader(content).read()
                add(subpacket)
                content = subpacket.trailing
            }
        }

        return OperatorPacket(
            version,
            trailing,
            type,
            subpackets
        )
    }

    private fun read(chars: Int): String {
        return buildString {
            repeat(chars) {
                append(remaining.removeFirst())
            }
        }
    }

    private companion object {
        private const val LITERAL_PACKET_TYPE_ID = 4
        private val LENGTH_TYPES_BY_ID = LengthType.values().associateBy(LengthType::id)
        private val OPERATOR_TYPES_BY_ID = OperatorType.values().associateBy(OperatorType::id)
    }
}
