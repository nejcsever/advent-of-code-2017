package day15

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day15/input.txt").readText()
    println("Part 1: ${DuelingGenerator(input).getJudgedScorePart1()}")
    println("Part 2: ${DuelingGenerator(input).getJudgedScorePart2()}")
}