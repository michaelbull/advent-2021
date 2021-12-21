package com.github.michaelbull.advent2021.day20

import com.github.michaelbull.advent2021.math.Vector2
import com.github.michaelbull.advent2021.math.Vector2BooleanMap

fun Sequence<String>.toImage(): Image {
    val it = iterator()
    val enhancement = it.next()

    require(it.next().isBlank())

    val lines = generateSequence { if (it.hasNext()) it.next() else null }.toList()
    val width = lines.first().length
    val height = lines.size

    val input = Vector2BooleanMap(width, height) { (x, y) ->
        val line = lines[y]
        val char = line[x]
        char == '#'
    }

    return Image(enhancement, input, false)
}

data class Image(
    val enhancement: String,
    val litPixels: Vector2BooleanMap,
    val litBorder: Boolean
) {

    fun litPixelCountAfter(enhancements: Int): Int {
        return enhanceSequence()
            .elementAt(enhancements)
            .litPixelCount()
    }

    fun litPixelCount(): Int {
        var count = 0

        for (x in litPixels.xRange) {
            for (y in litPixels.yRange) {
                val position = Vector2(x, y)

                if (litPixels[position]) {
                    count++
                }
            }
        }

        return count
    }

    fun enhance(): Image {
        return copy(
            litPixels = enhancePixels(),
            litBorder = enhanceBorder()
        )
    }

    fun enhanceSequence(): Sequence<Image> {
        return generateSequence(this, Image::enhance)
    }

    private fun enhancePixels(): Vector2BooleanMap {
        val enhanced = Vector2BooleanMap(litPixels.width + 2, litPixels.height + 2)

        for (enhancedY in enhanced.yRange) {
            for (enhancedX in enhanced.xRange) {
                var address = 0

                repeat(3) { deltaY ->
                    repeat(3) { deltaX ->
                        val x = (enhancedX - 1) + (deltaX - 1)
                        val y = (enhancedY - 1) + (deltaY - 1)
                        val position = Vector2(x, y)
                        val lit = litPixels.getOrNull(position) ?: litBorder
                        address = (address shl 1) or (if (lit) 1 else 0)
                    }
                }

                val enhancedLit = enhancement[address] == '#'
                val enhancedPosition = Vector2(enhancedX, enhancedY)
                enhanced[enhancedPosition] = enhancedLit
            }
        }

        return enhanced
    }

    private fun enhanceBorder(): Boolean {
        val end = if (litBorder) {
            enhancement.last()
        } else {
            enhancement.first()
        }

        return end == '#'
    }
}
