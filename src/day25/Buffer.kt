package day25

val STATE_ACTION_REGEX = ".*- Write the value (\\d+)\\. .*- Move one slot to the (right|left)\\. .*- Continue with state ([A-Z]).*".toRegex()

class Buffer(input: String) {
    private val beginningState = "Begin in state ([A-Z]).*".toRegex()
            .matchEntire(input.split("\n")[0])?.groups!![1]!!.value
    private val iterations = ".*Perform a diagnostic checksum after (\\d+) steps.*".toRegex()
            .matchEntire(input.split("\n")[1])?.groups!![1]!!.value.toInt()
    private val states: Map<String, State> = parseStates(input)

    private fun parseStates(input: String): Map<String, State> {
        val stateStrings = input.replace("\n", "").split("In state ")
        return (1 until stateStrings.size).map {
            val stateName = stateStrings[it][0].toString()
            val state = State(stateStrings[it])
            Pair(stateName, state)
        }.toMap()
    }

    fun diagnosticChecksum(): Int {
        var cursor = 0
        val tape = mutableMapOf<Int, Int>()
        var currentState = beginningState
        for (i in 0 until iterations) {
            val cursorValue = tape[cursor] ?: 0
            val step = states[currentState]!!.nextStep(cursorValue)
            if (step.newValue == 0) {
                tape.remove(cursor)
            } else {
                tape.put(cursor, step.newValue)
            }
            currentState = step.nextState
            cursor += step.cursorOffset
        }
        return tape.size
    }

    private class State(stateString: String) {
        private val stateActions: Map<Int, StateAction> = parseStateActions(stateString)

        private fun parseStateActions(stateString: String): Map<Int, StateAction> {
            val modifiedString = stateString.split("If the current value is ").drop(1)
            return modifiedString.map {
                val currentValue = Character.getNumericValue(it[0])
                val regexResult = STATE_ACTION_REGEX.matchEntire(it)?.groups!!
                val cursorOffset = if (regexResult[2]!!.value == "left") -1 else 1
                val step = StateAction(regexResult[1]!!.value.toInt(), regexResult[3]!!.value, cursorOffset)
                Pair(currentValue, step)
            }.toMap()
        }

        fun nextStep(cursorValue: Int): StateAction = stateActions[cursorValue]!!
    }

    private class StateAction(val newValue: Int, val nextState: String, val cursorOffset: Int)
}