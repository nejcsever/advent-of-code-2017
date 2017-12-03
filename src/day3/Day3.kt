package day3

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day3/input.txt").readText().toInt()
    println("Part 1: ${SpiralMemory.getManhattanDistance(input)}")
    println("Part 2: ${SpiralMemory.getStressTestData(input)}")
}