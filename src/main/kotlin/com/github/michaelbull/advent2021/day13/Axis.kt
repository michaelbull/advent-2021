package com.github.michaelbull.advent2021.day13

enum class Axis {
    X,
    Y
}

val AXES = Axis.values()
val AXES_LOWERCASE_NAMES = AXES.map { it.name.lowercase() }
val AXES_REGEX = AXES_LOWERCASE_NAMES.joinToString(separator = "", prefix = "[", postfix = "]").toRegex()
val AXES_BY_LOWERCASE_NAME = AXES.associateBy { it.name.lowercase() }

fun String.toAxis(): Axis {
    return requireNotNull(AXES_BY_LOWERCASE_NAME[this]) {
        "axis must be one of $AXES_LOWERCASE_NAMES, but was: $this"
    }
}
