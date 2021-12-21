package com.github.michaelbull.advent2021.day21

import com.github.michaelbull.advent2021.math.rem

class QuantumDiracDice(
    private val a: Player,
    private val b: Player
) {

    data class Result(
        val aWinCount: Long,
        val bWinCount: Long
    )

    data class Round(
        val playing: Player,
        val waiting: Player
    )

    fun play(): Result {
        val firstRound = Round(
            playing = a,
            waiting = b
        )

        return firstRound.play()
    }

    private fun quantumDiceRolls(rolls: Int) = sequence {
        roll(rolls)
    }

    private suspend fun SequenceScope<Int>.roll(remaining: Int, sum: Int = 0) {
        if (remaining <= 0) {
            yield(sum)
        } else {
            for (roll in DIE_SIDES) {
                roll(remaining - 1, roll + sum);
            }
        }
    }

    private fun Round.play(results: MutableMap<Round, Result> = mutableMapOf()): Result {
        return results.getOrPut(this) {
            var aWins = 0L
            var bWins = 0L

            val roundRolls = quantumDiceRolls(ROLLS_PER_ROUND)

            for (roll in roundRolls) {
                val moved = playing.move(roll)

                if (moved.isWinner()) {
                    aWins++
                } else {
                    val nextRound = Round(
                        playing = waiting,
                        waiting = moved
                    )

                    val nextResult = nextRound.play(results)
                    aWins += nextResult.bWinCount
                    bWins += nextResult.aWinCount
                }
            }

            Result(aWins, bWins)
        }
    }

    private fun Player.isWinner(): Boolean {
        return score >= WINNING_SCORE
    }

    private fun Player.move(amount: Int): Player {
        val next = (position + amount) % TRACK_RANGE

        return copy(
            position = next,
            score = score + next
        )
    }

    private companion object {
        private const val ROLLS_PER_ROUND = 3
        private const val WINNING_SCORE = 21
        private val TRACK_RANGE = 1..10
        private val DIE_SIDES = 1..3
    }
}
