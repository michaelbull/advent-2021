package com.github.michaelbull.advent2021.day21

class DiracDice(
    private val a: Player,
    private val b: Player
) {

    data class Result(
        val winner: Player,
        val loser: Player,
        val rollCount: Int
    )

    data class Round(
        val playing: Player,
        val waiting: Player,
        val rollCount: Int,
        val rolls: Sequence<Int>
    )

    fun play(): Result {
        val firstRound = Round(
            playing = a,
            waiting = b,
            rollCount = 0,
            rolls = deterministicDiceRolls()
        )

        return firstRound.play()
    }

    private fun deterministicDiceRolls() = generateSequence(DIE_SIDES.first) { roll ->
        if (roll == DIE_SIDES.last) {
            DIE_SIDES.first
        } else {
            roll + 1
        }
    }

    private tailrec fun Round.play(): Result {
        val roundRolls = rolls.take(ROLLS_PER_ROUND)
        val moved = playing.move(roundRolls.sum())

        return if (moved.isWinner()) {
            Result(
                winner = moved,
                loser = waiting,
                rollCount = rollCount + ROLLS_PER_ROUND
            )
        } else {
            val nextRound = Round(
                playing = waiting,
                waiting = moved,
                rollCount = rollCount + ROLLS_PER_ROUND,
                rolls = rolls.drop(ROLLS_PER_ROUND)
            )

            nextRound.play()
        }
    }

    private fun Player.isWinner(): Boolean {
        return score >= WINNING_SCORE
    }

    private fun Player.move(amount: Int): Player {
        val next = ((position + amount - 1) % TRACK_RANGE.last) + TRACK_RANGE.first

        return copy(
            position = next,
            score = score + next
        )
    }

    private companion object {
        private const val ROLLS_PER_ROUND = 3
        private const val WINNING_SCORE = 1000
        private val TRACK_RANGE = 1..10
        private val DIE_SIDES = 1..100
    }
}
