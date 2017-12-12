package day12

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day12/input.txt").readText()
    println("Part 1: ${DigitalPlumber.getElementsInAZeroGroup(input)}")
    println("Part 2: ${DigitalPlumber.getNoOfAllGroups(input)}")
}