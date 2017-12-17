package day17

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day17/input.txt").readText()
    val circularBuffer = CircularBuffer(input)
    println("Part 1: ${circularBuffer.getValueAfter(2017)}")
    println("Part 2: ${circularBuffer.getValueAfterZero(50_000_000)}")
}