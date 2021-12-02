package com.github.michaelbull.advent2021.day2

import com.github.michaelbull.advent2021.math.Vector3

class Submarine(position: Vector3 = Vector3.ZERO) {

    var position: Vector3 = position
        private set

    fun follow(course: PlannedCourse) {
        position = course.commands.fold(position) { acc, command ->
            acc + when (command.direction) {

                /* forward X increases the horizontal position by X units. */
                Direction.FORWARD -> Vector3(x = +command.x)

                /* down X increases the depth by X units */
                Direction.DOWN -> Vector3(y = +command.x)

                /* up X decreases the depth by X units */
                Direction.UP -> Vector3(y = -command.x)
            }
        }
    }

    fun followManual(course: PlannedCourse) {
        position = course.commands.fold(position) { acc, command ->
            acc + when (command.direction) {

                /* down X increases your aim by X units */
                Direction.DOWN -> Vector3(z = +command.x)

                /* up X decreases your aim by X units */
                Direction.UP -> Vector3(z = -command.x)

                /* forward X does two things */
                Direction.FORWARD -> Vector3(

                    /* It increases your horizontal position by X units */
                    x = +command.x,

                    /* It increases your depth by your aim multiplied by X */
                    y = acc.z * command.x
                )
            }
        }
    }
}
