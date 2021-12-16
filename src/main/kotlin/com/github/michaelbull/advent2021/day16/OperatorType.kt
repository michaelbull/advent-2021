package com.github.michaelbull.advent2021.day16

enum class OperatorType(val id: Int) {
    SUM(0),
    PRODUCT(1),
    MINIMUM(2),
    MAXIMUM(3),
    GREATER_THAN(5),
    LESS_THAN(6),
    EQUAL_TO(7)
}

val OPERATOR_TYPES = OperatorType.values()
val OPERATOR_TYPES_BY_ID = OPERATOR_TYPES.associateBy(OperatorType::id)
