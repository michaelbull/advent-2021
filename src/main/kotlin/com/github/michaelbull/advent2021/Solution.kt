package com.github.michaelbull.advent2021

fun interface Solution<T : Any, V: Any> {
    operator fun invoke(input: T): V
}
