package com.github.michaelbull.advent2021.day20

import com.github.michaelbull.advent2021.Puzzle

object Day20 : Puzzle<Image, Int>(day = 20) {

    override fun parse(input: Sequence<String>): Image {
        return input.toImage()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2
    )

    fun part1(image: Image): Int {
        return image.litPixelCountAfter(enhancements = 2)
    }

    fun part2(image: Image): Int {
        return image.litPixelCountAfter(enhancements = 50)
    }
}
