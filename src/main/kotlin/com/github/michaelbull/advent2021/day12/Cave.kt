package com.github.michaelbull.advent2021.day12

@JvmInline
value class Cave(private val name: String) {

    val isBig: Boolean
        get() = name.all(Char::isUpperCase)

    val isSmall: Boolean
        get() = name.all(Char::isLowerCase)

    companion object {
        val START = Cave("start")
        val END = Cave("end")
    }
}
