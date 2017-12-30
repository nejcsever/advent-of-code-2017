package day25

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day25/input.txt").readText()
    println("Part 1: ${Buffer(input).diagnosticChecksum()}")
}