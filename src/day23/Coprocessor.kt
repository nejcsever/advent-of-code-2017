package day23

class Coprocessor(input: String) {
    private val commands = input.split("\n")

    fun numberOfMultiplications(): Int {
        tailrec fun countMultiplications(count: Int, programState: ProgramState): Int {
            if (!programState.isRunning(commands)) {
                return count
            }
            val command = commands[programState.step].split(" ")
            val commandName = command[0]
            val x = programState.numberOrRegisterValue(command[1])
            val y = if (command.size > 2) programState.numberOrRegisterValue(command[2]) else 0
            return when (commandName) {
                "set" -> countMultiplications(count, programState.addToRegister(command[1], y))
                "sub" -> countMultiplications(count, programState.addToRegister(command[1], x - y))
                "mul" -> countMultiplications(count + 1, programState.addToRegister(command[1], x * y))
                else -> countMultiplications(count, programState.jump(if (x != 0L) y.toInt() else 1))
            }
        }
        return countMultiplications(0, ProgramState(mapOf(), 0))
    }

    fun valueInRegisterH(): Int {
        var b = 109300
        val c = 126300
        var h = 0
        while (true) {
            var d = 2
            while (true) {
                if (b % d == 0) {
                    val e = b / d
                    if (e > 1) h++
                    break
                }
                if (d - b == 0) break
                d++
            }
            if (b - c == 0) break
            b += 17
        }
        return h
    }

    private class ProgramState(val registers: Map<String, Long>, var step: Int) {

        fun numberOrRegisterValue(stringValue: String) = stringValue.toLongOrNull() ?: registers[stringValue] ?: 0

        fun addToRegister(register: String, value: Long): ProgramState = ProgramState(registers + Pair(register, value), step + 1)

        fun jump(steps: Int): ProgramState = ProgramState(registers, step + steps)

        fun isRunning(commands: List<Any>): Boolean = step >= 0 && step < commands.size
    }
}