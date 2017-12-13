package day12

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day12/input.txt").readText()
    println("Part 1: ${DigitalPlumber(input).getNoOfElementsForProgramZero()}")
    println("Part 2: ${DigitalPlumber(input).getNoOfGroups()}")
}