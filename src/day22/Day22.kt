package day22

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day22/input.txt").readText()
    println("Part 1: ${Virus(input).infectionCountPart1(10_000)}")
    println("Part 2: ${Virus(input).infectionCountPart2(10_000_000)}")
}