package day5

object Jumper {
    fun noOfSteps(input: List<Int>): Int {
        val inputArray = input.toTypedArray()
        var steps = 0
        var pos = 0
        while (0 <= pos && pos < inputArray.size) {
            steps++
            val valAtPos = inputArray[pos]
            inputArray[pos] = valAtPos + 1
            pos += valAtPos
        }
        return steps
    }

    fun noOfStepsWithNegativeJumps(input: List<Int>): Int {
        val inputArray = input.toTypedArray()
        var steps = 0
        var pos = 0
        while (0 <= pos && pos < inputArray.size) {
            steps++
            val valAtPos = inputArray[pos]
            inputArray[pos] = valAtPos + if (valAtPos < 3) 1 else -1
            pos += valAtPos
        }
        return steps
    }
}