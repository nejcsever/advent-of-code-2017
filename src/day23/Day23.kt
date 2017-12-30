package day23

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day23/input.txt").readText()
    println("Part 1: ${Coprocessor(input).numberOfMultiplications()}")
    println("Part 2: ${Coprocessor(input).valueInRegisterH()}")
}