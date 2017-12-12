package day11

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day11/input.txt").readText()
    println("Part 1: ${HexGrid.getNoOfSteps(input)}")
    println("Part 2: ${HexGrid.getMaxDistance(input)}")
}