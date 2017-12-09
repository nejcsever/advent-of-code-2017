package day7

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day7/input.txt").readText()
    println("Part 1: ${ProgramTowers.getRootProgramName(input)}")
    println("Part 2: ${ProgramTowers.getCorrectTowerWeight(input)}")
}