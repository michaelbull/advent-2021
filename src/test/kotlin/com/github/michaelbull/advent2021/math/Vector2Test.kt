package com.github.michaelbull.advent2021.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Vector2Test {

    @Test
    fun plus() {
        assertEquals(Vector2(25, 17), Vector2(18, 10) + 7)
        assertEquals(Vector2(10, 15), Vector2(4, 10) + Vector2(6, 5))
    }

    @Test
    fun minus() {
        assertEquals(Vector2(44, 54), Vector2(50, 60) - 6)
        assertEquals(Vector2(12, 50), Vector2(20, 100) - Vector2(8, 50))
    }

    @Test
    fun times() {
        assertEquals(Vector2(12, 24), Vector2(4, 8) * 3)
        assertEquals(Vector2(144, 10), Vector2(12, 2) * Vector2(12, 5))
    }

    @Test
    fun div() {
        assertEquals(Vector2(10, 5), Vector2(30, 15) / 3)
        assertEquals(Vector2(12, 2), Vector2(144, 10) / Vector2(12, 5))
    }
}
