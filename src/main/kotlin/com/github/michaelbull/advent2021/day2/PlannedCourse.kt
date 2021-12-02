package com.github.michaelbull.advent2021.day2

data class PlannedCourse(
    val commands: Sequence<Command> = emptySequence()
)

fun Sequence<String>.toPlannedCourse(): PlannedCourse {
    return PlannedCourse(this.map(String::toCommand))
}
