package com.github.michaelbull.advent2021.day24

fun String.toVariableName(): VariableName {
    return when (this) {
        "w" -> VariableName.W
        "x" -> VariableName.X
        "y" -> VariableName.Y
        "z" -> VariableName.Z
        else -> throw IllegalArgumentException("unknown variable name: '$this'")
    }
}

sealed class VariableName {
    object W : VariableName() {
        override fun toString() = "w"
    }

    object X : VariableName() {
        override fun toString() = "x"
    }

    object Y : VariableName() {
        override fun toString() = "y"
    }

    object Z : VariableName() {
        override fun toString() = "z"
    }
}
