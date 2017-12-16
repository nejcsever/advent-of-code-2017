package day16

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day16/input.txt").readText()
    println("Part 1: ${Dancer(input, 1).dance()}")
    println("Part 2: ${Dancer(input, 1_000_000_000).dance()}")
}