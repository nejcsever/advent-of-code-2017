package day19

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day19/input.txt").readText()
    println("Part 1: ${Router(input).getLettersOnPath()}")
    println("Part 2: ${Router(input).getNumberOfSteps()}")
}