package day9

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day9/input.txt").readText()
    val (score, garbage) = StreamProcessor.calculateGroupScoreAndGarbage(input)
    println("Part 1: $score")
    println("Part 2: $garbage")
}