package com.github.michaelbull.advent2021.day24

class ArithmeticLogicUnit {

    private val memory = mutableMapOf<VariableName, Long>().withDefault { 0 }

    private lateinit var onInput: (VariableName) -> Long

    fun onInput(action: (VariableName) -> Long) {
        this.onInput = action
    }

    fun execute(instructions: Iterable<Instruction>) {
        return instructions.forEach(::execute)
    }

    fun execute(instruction: Instruction) {
        val variable = instruction.variable
        val value = memory.getValue(variable)

        memory[variable] = when (instruction) {
            is Instruction.Inp -> onInput(variable)

            is Instruction.Literal -> {
                val operand = instruction.operand

                when (instruction) {
                    is Instruction.Literal.Add -> value + operand
                    is Instruction.Literal.Mul -> value * operand
                    is Instruction.Literal.Div -> value / operand
                    is Instruction.Literal.Mod -> value % operand
                    is Instruction.Literal.Eql -> if (value == operand) 1 else 0
                }
            }

            is Instruction.Variable -> {
                val operand = memory.getValue(instruction.operand)

                when (instruction) {
                    is Instruction.Variable.Add -> value + operand
                    is Instruction.Variable.Mul -> value * operand
                    is Instruction.Variable.Div -> value / operand
                    is Instruction.Variable.Mod -> value % operand
                    is Instruction.Variable.Eql -> if (value == operand) 1 else 0
                }
            }
        }
    }

    operator fun set(variable: VariableName, value: Long) {
        memory[variable] = value
    }

    operator fun get(variable: VariableName): Long {
        return memory.getValue(variable)
    }

    fun isValid(): Boolean {
        return this[VariableName.Z] == 0L
    }
}
