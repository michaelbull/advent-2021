package com.github.michaelbull.advent2021.math

/* https://en.wikipedia.org/wiki/Triangular_number */

fun triangular(n: Int): Int {
    return (n * (n + 1)) / 2
}

fun triangular(n: Long): Long {
    return (n * (n + 1)) / 2
}
