package day8

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day8/input.txt").readText()
    val (maxRegisterValue, highestAllocationValue) = Registers.calculate(input)
    println("Part 1: $maxRegisterValue")
    println("Part 2: $highestAllocationValue")
}