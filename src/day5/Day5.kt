package day5

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day5/input.txt").readText()
    val inputArray = input.split("\n").map { it.toInt() }
    println("Part 1: ${Jumper.noOfSteps(inputArray)}")
    println("Part 2: ${Jumper.noOfStepsWithNegativeJumps(inputArray)}")
}