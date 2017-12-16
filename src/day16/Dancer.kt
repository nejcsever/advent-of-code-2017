package day16

class Dancer(val input: String, val iterations: Int) {
    private val danceMoves: List<DanceMove>

    init {
        danceMoves = input.split(",").map { fullDanceMoveString ->
            val danceMoveType = fullDanceMoveString[0]
            val danceMoveArgs = fullDanceMoveString.substring(1).split("/")
            when (danceMoveType) {
                's' -> MoveS(danceMoveArgs[0].toInt())
                'x' -> MoveX(danceMoveArgs[0].toInt(), danceMoveArgs[1].toInt())
                else -> MoveP(danceMoveArgs[0].first(), danceMoveArgs[1].first())
            }
        }
    }

    fun dance(): String {
        tailrec fun getFirstCycleStates(seen: Set<String>, seenList: List<String>, previous: String): List<String> {
            if (seen.contains(previous)) {
                return seenList
            }
            val newStep = danceMoves.fold(previous, { danceState, move -> move.execute(danceState) })
            return getFirstCycleStates(seen + previous, seenList + previous, newStep)
        }

        val initialDanceState = ('a'..'p').joinToString(separator = "")
        val cycleStates = getFirstCycleStates(setOf(), listOf(), initialDanceState)
        return cycleStates[iterations % cycleStates.size]
    }

    private interface DanceMove {
        fun execute(danceState: String): String
    }

    private class MoveS(val noOfLastElements: Int) : DanceMove {
        override fun execute(danceState: String): String {
            return danceState.spin(noOfLastElements)
        }
    }

    private class MoveX(val i: Int, val j: Int) : DanceMove {
        override fun execute(danceState: String): String {
            return danceState.swap(danceState[i], danceState[j])
        }
    }

    private class MoveP(val x: Char, val y: Char) : DanceMove {
        override fun execute(danceState: String): String {
            return danceState.swap(danceState[danceState.indexOf(x)], danceState[danceState.indexOf(y)])
        }
    }
}

private fun String.swap(c1: Char, c2: Char): String {
    return this.replace(c1, '-').replace(c2, c1).replace('-', c2)
}

private fun String.spin(amount: Int): String {
    return this.takeLast(amount) + this.dropLast(amount)
}