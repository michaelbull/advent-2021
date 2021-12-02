package com.github.michaelbull.advent2021.day2

data class Command(
    val direction: Direction,
    val x: Int
)

private val DIRECTION_REGEX = DIRECTIONS.joinToString("|") { it.name.lowercase() }.toRegex()
private val UNIT_REGEX = "\\d+".toRegex()
private val COMMAND_REGEX = "($DIRECTION_REGEX) ($UNIT_REGEX)".toRegex()

fun String.toCommand(): Command {
    val values = requireNotNull(COMMAND_REGEX.matchEntire(this)) {
        "\"$this\" must match \"$COMMAND_REGEX\""
    }.groupValues

    return Command(
        direction = values[1].toDirection(),
        x = values[2].toInt()
    )
}
