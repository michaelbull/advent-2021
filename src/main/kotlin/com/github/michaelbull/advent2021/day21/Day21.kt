package com.github.michaelbull.advent2021.day21

import com.github.michaelbull.advent2021.Puzzle
import kotlin.math.max

object Day21 : Puzzle<Pair<Player, Player>, Number>(day = 21) {

    override fun parse(input: Sequence<String>): Pair<Player, Player> {
        val (a, b) = input.map(String::toPlayer).toList()
        return Pair(a, b)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(players: Pair<Player, Player>): Number {
        val (_, loser, rolls) = DiracDice(players.first, players.second).play()
        return loser.score * rolls
    }

    fun part2(players: Pair<Player, Player>): Number {
        val (aWins, bWins) = QuantumDiracDice(players.first, players.second).play()
        return max(aWins, bWins)
    }
}
