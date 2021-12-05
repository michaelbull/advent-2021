package com.github.michaelbull.advent2021.math

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector2RangeTest {

    @Test
    fun contains() {
        val range = Vector2(0, 0) .. Vector2(5, 5)
        assertTrue(range.contains(Vector2(0, 0)))
        assertTrue(range.contains(Vector2(5, 5)))
        assertTrue(range.contains(Vector2(3, 3)))
        assertFalse(range.contains(Vector2(-1, 0)))
        assertFalse(range.contains(Vector2(0, 6)))
        assertFalse(range.contains(Vector2(20, 8)))
    }

    @Test
    fun isEmpty() {
        assertFalse((Vector2(0, 0)..Vector2(0, 0)).isEmpty())
        assertFalse((Vector2(0, 0)..Vector2(5, 5)).isEmpty())
    }
}
