package day15

class DuelingGenerator(val input: String) {
    private val startingValueA: Long = input.split("\n")[0].split(" ").last().toLong()
    private val startingValueB: Long = input.split("\n")[1].split(" ").last().toLong()
    private val FACTOR_A = 16807L
    private val FACTOR_B = 48271L
    private val MODULO = 2147483647L
    private val MOD_CONDITION_A = 4L
    private val MOD_CONDITION_B = 8L

    fun getJudgedScorePart1(): Int {
        val generatorA = generateSequence(startingValueA) { (it * FACTOR_A) % MODULO }.iterator()
        val generatorB = generateSequence(startingValueB) { (it * FACTOR_B) % MODULO }.iterator()
        return (1L..40_000_000L).count { generatorA.next().matchesLast16Bits(generatorB.next()) }
    }

    fun getJudgedScorePart2(): Int {
        val generatorA = generateSequence(startingValueA) { ((it * FACTOR_A) % MODULO) }.filter { it % MOD_CONDITION_A == 0L }.iterator()
        val generatorB = generateSequence(startingValueB) { ((it * FACTOR_B) % MODULO) }.filter { it % MOD_CONDITION_B == 0L }.iterator()
        return (1L..5_000_000L).count { generatorA.next().matchesLast16Bits(generatorB.next()) }
    }
}

private fun Long.matchesLast16Bits(that: Long): Boolean {
    return this.toString(16).takeLast(4) == that.toString(16).takeLast(4)
}