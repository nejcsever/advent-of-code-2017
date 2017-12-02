package day2


fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day2/input.txt").readText()
    val spreadsheet = input.split("\n").map { it.split("\t").map { it.toInt() } }
    println("Part 1: ${CorruptionChecksum.calculateSumMinMax(spreadsheet)}")
    println("Part 2: ${CorruptionChecksum.calculateSumDivision(spreadsheet)}")
}