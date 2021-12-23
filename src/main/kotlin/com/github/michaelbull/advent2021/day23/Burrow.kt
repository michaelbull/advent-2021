package com.github.michaelbull.advent2021.day23

import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun Sequence<String>.toBurrow(): Burrow {
    val lines = toList().dropWhile(String::isEmpty)
    val hallway = lines[1].filter { it == Cell.OPEN_SPACE }.toCharArray()
    val roomCapacity = lines.sumOf { line -> line.count { it == Cell.AMBER } }

    return Burrow(
        roomCapacity = roomCapacity,
        hallway = hallway,
        rooms = arrayOf(
            charArrayOf(lines[3][3], lines[2][3]),
            charArrayOf(lines[3][5], lines[2][5]),
            charArrayOf(lines[3][7], lines[2][7]),
            charArrayOf(lines[3][9], lines[2][9]),
        )
    )
}

data class Burrow(
    val roomCapacity: Int,
    val hallway: CharArray,
    val rooms: Array<CharArray>,
) {

    private val roomXValues = rooms.indices.map { it.toHallwayX() }

    fun withExtra(extra: List<CharArray>): Burrow {
        require(extra.size == 2)
        require(roomCapacity == 2)

        val newCapacity = roomCapacity + extra.size

        val newRooms = Array(rooms.size) { roomIndex ->
            CharArray(newCapacity) { i ->
                when (i) {
                    0 -> rooms[roomIndex][0]
                    1 -> extra[1][roomIndex]
                    2 -> extra[0][roomIndex]
                    3 -> rooms[roomIndex][1]
                    else -> throw IllegalStateException()
                }
            }
        }

        return copy(
            roomCapacity = newCapacity,
            rooms = newRooms
        )
    }

    fun shortestPath(): Int {
        val costs = mutableMapOf<Burrow, Int>()
        val queue = PriorityQueue(compareBy(costs::get))

        costs[this] = 0
        queue += this

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val lowestCost = costs.getValue(current)

            if (current.isComplete()) {
                return lowestCost
            }

            for ((cost, burrow) in current.potentialBurrows()) {
                val lowestPotentialCost = costs[burrow]
                val cumulativeCost = lowestCost + cost

                if (lowestPotentialCost == null || cumulativeCost < lowestPotentialCost) {
                    costs[burrow] = cumulativeCost
                    queue += burrow
                }
            }
        }

        throw IllegalArgumentException()
    }

    private fun potentialBurrows() = sequence {
        roomToHallway()
        hallwayToRoom()
    }

    private suspend fun SequenceScope<PotentialBurrow>.hallwayToRoom() {
        for ((hallwayX, cell) in hallway.withIndex()) {
            if (cell == Cell.OPEN_SPACE) {
                continue
            }

            val roomIndex = roomForAmphipod(cell)
            val roomX = roomIndex.toHallwayX()

            val xRange = (min(hallwayX, roomX)..max(hallwayX, roomX)) - hallwayX
            if (xRange.any(::isHallwayOccupied)) {
                continue
            }

            val room = rooms[roomIndex]

            if (room hasSpaceFor cell) {
                val newHallway = hallway.copyOf()
                val newRoom = room.copyOf(room.size + 1)
                val newRooms = rooms.copyOf()

                newHallway[hallwayX] = Cell.OPEN_SPACE
                newRoom[newRoom.size - 1] = cell
                newRooms[roomIndex] = newRoom

                val newBurrow = copy(
                    hallway = newHallway,
                    rooms = newRooms
                )

                val stepsOut = (roomCapacity - newRoom.size) + 1
                val stepsAcross = abs(roomX - hallwayX)
                val steps = stepsOut + stepsAcross
                val cost = steps * cell.energy()

                val node = PotentialBurrow(cost, newBurrow)
                yield(node)
            }
        }
    }

    private suspend fun SequenceScope<PotentialBurrow>.roomToHallway() {
        for ((roomIndex, room) in rooms.withIndex()) {
            if (isComplete(roomIndex, room)) {
                continue
            }

            val amphipod = room.lastOrNull() ?: continue

            for ((hallwayX, cell) in hallway.withIndex()) {
                if (cell != Cell.OPEN_SPACE) {
                    continue
                }

                if (isOutsideRoom(hallwayX)) {
                    continue
                }

                val roomX = roomIndex.toHallwayX()

                val xRange = min(hallwayX, roomX)..max(hallwayX, roomX)
                if (xRange.any(::isHallwayOccupied)) {
                    continue
                }

                val newHallway = hallway.copyOf()
                val newRoom = room.copyOf(room.size - 1)
                val newRooms = rooms.copyOf()

                newHallway[hallwayX] = amphipod
                newRooms[roomIndex] = newRoom

                val newBurrow = copy(
                    hallway = newHallway,
                    rooms = newRooms
                )

                val stepsAcross = abs(roomX - hallwayX)
                val stepsIn = (roomCapacity - room.size) + 1
                val totalSteps = stepsAcross + stepsIn
                val cost = totalSteps * amphipod.energy()

                yield(PotentialBurrow(cost, newBurrow))
            }
        }
    }

    private fun isComplete(): Boolean {
        return rooms.withIndex().all { (roomIndex, room) ->
            isComplete(roomIndex, room)
        }
    }

    private fun isHallwayOccupied(x: Int): Boolean {
        return hallway[x] != Cell.OPEN_SPACE
    }

    private fun Int.toHallwayX(): Int {
        return (this * 2) + 2
    }

    private fun isComplete(roomIndex: Int, room: CharArray): Boolean {
        return room.size == roomCapacity && room.all { it == amphipodForRoom(roomIndex) }
    }

    private fun isOutsideRoom(hallwayX: Int): Boolean {
        return hallwayX in roomXValues
    }

    private infix fun CharArray.hasSpaceFor(amphipod: Char): Boolean {
        return size < roomCapacity && all { it == amphipod }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Burrow

        if (roomCapacity != other.roomCapacity) return false
        if (!hallway.contentEquals(other.hallway)) return false
        if (!rooms.contentDeepEquals(other.rooms)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = roomCapacity
        result = 31 * result + hallway.contentHashCode()
        result = 31 * result + rooms.contentDeepHashCode()
        return result
    }

    private fun Char.energy(): Int {
        return when (this) {
            Cell.AMBER -> 1
            Cell.BRONZE -> 10
            Cell.COPPER -> 100
            Cell.DESERT -> 1000
            else -> throw IllegalArgumentException()
        }
    }

    private fun amphipodForRoom(roomIndex: Int): Char {
        return when (roomIndex) {
            0 -> Cell.AMBER
            1 -> Cell.BRONZE
            2 -> Cell.COPPER
            3 -> Cell.DESERT
            else -> throw IllegalArgumentException()
        }
    }

    private fun roomForAmphipod(amphipod: Char): Int {
        return when (amphipod) {
            Cell.AMBER -> 0
            Cell.BRONZE -> 1
            Cell.COPPER -> 2
            Cell.DESERT -> 3
            else -> throw IllegalArgumentException()
        }
    }
}
