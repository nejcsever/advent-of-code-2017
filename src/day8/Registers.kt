package day8

object Registers {
    fun calculate(input: String): Pair<Int, Int> {
        val registers = mutableMapOf<String, Int>()
        val conditions = input.split("\n")
        var highestValue = 0

        for (condition in conditions) {
            val split = condition.split(" ")
            val name = split[0]
            val action = split[1]
            val amount = split[2].toInt()
            val conditionRegisterName = split[4]
            val conditionString = split[5]
            val conditionValue = split[6].toInt()

            var registerValue = registers[name] ?: 0
            val conditionRegisterValue = registers[conditionRegisterName] ?: 0
            if (checkCondition(conditionRegisterValue, conditionString, conditionValue)) {
                when (action) {
                    "inc" -> registerValue += amount
                    "dec" -> registerValue -= amount
                }
            }
            registers.put(name, registerValue)

            if (registerValue > highestValue) {
                highestValue = registerValue
            }
        }
        return Pair(registers.values.max()!!, highestValue)
    }

    fun checkCondition(conditionRegisterValue: Int, condition: String, conditionValue: Int): Boolean {
        when (condition) {
            ">" -> return conditionRegisterValue > conditionValue
            "<" -> return conditionRegisterValue < conditionValue
            "!=" -> return conditionRegisterValue != conditionValue
            ">=" -> return conditionRegisterValue >= conditionValue
            "<=" -> return conditionRegisterValue <= conditionValue
            "==" -> return conditionRegisterValue == conditionValue
            else -> return false
        }
    }
}