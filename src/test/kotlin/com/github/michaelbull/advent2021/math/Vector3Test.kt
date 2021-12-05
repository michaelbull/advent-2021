package com.github.michaelbull.advent2021.math

import kotlin.test.Test
import kotlin.test.assertEquals

class Vector3Test {

    @Test
    fun plus() {
        assertEquals(Vector3(25, 17, 5), Vector3(18, 10, -2) + 7)
        assertEquals(Vector3(10, 15, 20), Vector3(4, 10, 12) + Vector3(6, 5, 8))
    }

    @Test
    fun minus() {
        assertEquals(Vector3(44, 54, 34), Vector3(50, 60, 40) - 6)
        assertEquals(Vector3(12, 50, 36), Vector3(20, 100, 50) - Vector3(8, 50, 14))
    }

    @Test
    fun times() {
        assertEquals(Vector3(12, 24, 36), Vector3(4, 8, 12) * 3)
        assertEquals(Vector3(144, 10, 40), Vector3(12, 2, 8) * Vector3(12, 5, 5))
    }

    @Test
    fun div() {
        assertEquals(Vector3(10, 5, 33), Vector3(30, 15, 99) / 3)
        assertEquals(Vector3(12, 2, 6), Vector3(144, 10, 36) / Vector3(12, 5, 6))
    }
}
