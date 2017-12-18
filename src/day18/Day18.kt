package day18

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day18/input.txt").readText()
    println("Part 1: ${Duet(input).getLastRecoveredSound()}")
    println("Part 2: ${Duet(input).duel()}")
}