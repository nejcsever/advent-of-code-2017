package day10

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day10/input.txt").readText()
    println("Part 1: ${KnotHash.sparseHashCode(input)}")
    println("Part 2: ${KnotHash.digest(input)}")
}