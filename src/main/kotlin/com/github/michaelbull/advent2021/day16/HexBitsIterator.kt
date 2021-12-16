package com.github.michaelbull.advent2021.day16

fun CharSequence.hexBitsIterator() = HexBitsIterator(this)

class HexBitsIterator(private val chars: CharSequence) : BooleanIterator() {

    private var charIndex = 0
    private var digitIndex = 0
    private var digit = 0

    override fun hasNext(): Boolean {
        return digitIndex != 0 || charIndex < chars.length
    }

    override fun nextBoolean(): Boolean {
        if (digitIndex == 0) {
            digit = chars[charIndex++].digitToInt(16)
            digitIndex = DIGIT_LENGTH
        }

        digitIndex--

        return ((digit shr digitIndex) and 0x1) != 0
    }

    private companion object {
        private const val DIGIT_LENGTH = 4
    }
}
