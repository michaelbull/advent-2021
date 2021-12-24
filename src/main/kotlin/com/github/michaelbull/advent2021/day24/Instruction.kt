package com.github.michaelbull.advent2021.day24

private val LITERAL_REGEX = "-?\\d+".toRegex()
private val VARIABLE_REGEX = "[wxyz]".toRegex()
private val OPERAND_REGEX = "$LITERAL_REGEX|$VARIABLE_REGEX".toRegex()

val INP_INSTRUCTION_REGEX = "inp ($VARIABLE_REGEX)".toRegex()
val OPERAND_INSTRUCTION_REGEX = "(add|mul|div|mod|eql) ($VARIABLE_REGEX) ($OPERAND_REGEX)".toRegex()

fun String.toInstruction(): Instruction {
    return when {
        this matches INP_INSTRUCTION_REGEX -> toInpInstruction()
        this matches OPERAND_INSTRUCTION_REGEX -> toOperandInstruction()
        else -> throw IllegalArgumentException("unknown instruction: '$this'")
    }
}

fun String.toInpInstruction(): Instruction.Inp {
    val result = requireNotNull(INP_INSTRUCTION_REGEX.matchEntire(this)) {
        "inp instruction must match '$INP_INSTRUCTION_REGEX', but was: '$this'"
    }

    val variable = result.groupValues[1].toVariableName()
    return Instruction.Inp(variable)
}

fun String.toOperandInstruction(): Instruction {
    val result = requireNotNull(OPERAND_INSTRUCTION_REGEX.matchEntire(this)) {
        "operand instruction must match '$OPERAND_INSTRUCTION_REGEX', but was: '$this'"
    }

    val instruction = result.groupValues[1]
    val variable = result.groupValues[2].toVariableName()
    val operand = result.groupValues[3]

    if (operand matches VARIABLE_REGEX) {
        val other = operand.toVariableName()

        return when (instruction) {
            "add" -> Instruction.Variable.Add(variable, other)
            "mul" -> Instruction.Variable.Mul(variable, other)
            "div" -> Instruction.Variable.Div(variable, other)
            "mod" -> Instruction.Variable.Mod(variable, other)
            "eql" -> Instruction.Variable.Eql(variable, other)
            else -> throw IllegalArgumentException("invalid instruction: $instruction")
        }
    } else if (operand matches LITERAL_REGEX) {
        val literal = operand.toLong()

        return when (instruction) {
            "add" -> Instruction.Literal.Add(variable, literal)
            "mul" -> Instruction.Literal.Mul(variable, literal)
            "div" -> Instruction.Literal.Div(variable, literal)
            "mod" -> Instruction.Literal.Mod(variable, literal)
            "eql" -> Instruction.Literal.Eql(variable, literal)
            else -> throw IllegalArgumentException("invalid instruction: $instruction")
        }
    } else {
        throw IllegalArgumentException("invalid operand: $operand")
    }
}

sealed interface Instruction {
    val variable: VariableName

    data class Inp(override val variable: VariableName) : Instruction

    sealed interface Variable : Instruction {
        val operand: VariableName

        data class Add(override val variable: VariableName, override val operand: VariableName) : Variable
        data class Mul(override val variable: VariableName, override val operand: VariableName) : Variable
        data class Div(override val variable: VariableName, override val operand: VariableName) : Variable
        data class Mod(override val variable: VariableName, override val operand: VariableName) : Variable
        data class Eql(override val variable: VariableName, override val operand: VariableName) : Variable
    }

    sealed interface Literal : Instruction {
        val operand: Long

        data class Add(override val variable: VariableName, override val operand: Long) : Literal
        data class Mul(override val variable: VariableName, override val operand: Long) : Literal
        data class Div(override val variable: VariableName, override val operand: Long) : Literal
        data class Mod(override val variable: VariableName, override val operand: Long) : Literal
        data class Eql(override val variable: VariableName, override val operand: Long) : Literal
    }
}
