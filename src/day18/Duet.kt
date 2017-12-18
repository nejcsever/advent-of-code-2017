package day18

class Duet(input: String) {
    private val commands = input.split("\n")

    fun getLastRecoveredSound(): Long {
        tailrec fun lastRecoveredSound(lastSoundPlayed: Long, programState: ProgramState): Long {
            val command = commands[programState.step].split(" ")
            val commandName = command[0]
            val x = programState.numberOrRegisterValue(command[1])
            val y = if (command.size > 2) programState.numberOrRegisterValue(command[2]) else 0
            if (commandName == "rcv" && x != 0L) {
                return lastSoundPlayed
            }
            return when (commandName) {
                "snd" -> lastRecoveredSound(x, programState.jump(1))
                "set" -> lastRecoveredSound(lastSoundPlayed, programState.addToRegister(command[1], y))
                "add" -> lastRecoveredSound(lastSoundPlayed, programState.addToRegister(command[1], x + y))
                "mul" -> lastRecoveredSound(lastSoundPlayed, programState.addToRegister(command[1], x * y))
                "mod" -> lastRecoveredSound(lastSoundPlayed, programState.addToRegister(command[1], x % y))
                "jgz" -> lastRecoveredSound(lastSoundPlayed, programState.jump(if (x > 0L) y.toInt() else 1))
                else -> lastRecoveredSound(lastSoundPlayed, programState.jump(1))
            }
        }
        return lastRecoveredSound(0, ProgramState(mapOf(), 0))
    }

    fun duel(): Long {
        tailrec fun runUntilNoMessages(programState: ProgramState): ProgramState {
            val command = commands[programState.step].split(" ")
            val commandName = command[0]
            val X = programState.numberOrRegisterValue(command[1])
            val Y = if (command.size > 2) programState.numberOrRegisterValue(command[2]) else 0
            if (commandName == "rcv") {
                if (programState.messagesIn.isEmpty()) {
                    return programState
                } else {
                    return runUntilNoMessages(programState.receiveMessage(command[1]))
                }
            }
            return when (commandName) {
                "snd" -> runUntilNoMessages(programState.sendMessage(X))
                "set" -> runUntilNoMessages(programState.addToRegister(command[1], Y))
                "add" -> runUntilNoMessages(programState.addToRegister(command[1], X + Y))
                "mul" -> runUntilNoMessages(programState.addToRegister(command[1], X * Y))
                "mod" -> runUntilNoMessages(programState.addToRegister(command[1], X % Y))
                "jgz" -> runUntilNoMessages(programState.jump(if (X > 0L) Y.toInt() else 1))
                else -> runUntilNoMessages(programState) // Should not happen
            }
        }

        fun execDuel(oldState0: ProgramState, oldState1: ProgramState): Long {
            val input0 = ProgramState(oldState1.messagesOut, oldState1.messagesIn, oldState0.registers, oldState0.step, oldState0.sentCounter)
            val input1 = ProgramState(oldState0.messagesOut, oldState0.messagesIn, oldState1.registers, oldState1.step, oldState1.sentCounter)
            val newState0 = runUntilNoMessages(input0)
            val newState1 = runUntilNoMessages(input1)
            if (oldState0.messagesIn == newState0.messagesIn && oldState1.messagesIn == newState1.messagesIn &&
                    oldState0.messagesOut == newState0.messagesOut && oldState1.messagesOut == newState1.messagesOut) {
                return newState1.sentCounter
            }
            return execDuel(newState0, newState1)
        }

        val initState0 = ProgramState(listOf(), listOf(), mapOf(Pair("p", 0L)), 0, 0)
        val initState1 = ProgramState(listOf(), listOf(), mapOf(Pair("p", 1L)), 0, 0)
        return execDuel(initState0, initState1)
    }

    private class ProgramState(val messagesIn: List<Long>, val messagesOut: List<Long>, val registers: Map<String, Long>, val step: Int, val sentCounter: Long) {

        constructor(registers: Map<String, Long>, step: Int) : this(listOf<Long>(), listOf<Long>(), registers, step, 0L)

        fun numberOrRegisterValue(stringValue: String) = stringValue.toLongOrNull() ?: registers[stringValue] ?: 0

        fun receiveMessage(toRegister: String): ProgramState {
            return ProgramState(messagesIn.drop(1), messagesOut, registers + Pair(toRegister, messagesIn[0]), step + 1, sentCounter)
        }

        fun sendMessage(msg: Long): ProgramState {
            return ProgramState(messagesIn, messagesOut + msg, registers, step + 1, sentCounter + 1)
        }

        fun addToRegister(register: String, value: Long): ProgramState {
            return ProgramState(messagesIn, messagesOut, registers + Pair(register, value), step + 1, sentCounter)
        }

        fun jump(steps: Int): ProgramState {
            return ProgramState(messagesIn, messagesOut, registers, step + steps, sentCounter)
        }
    }
}