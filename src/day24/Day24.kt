package day24

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day24/input.txt").readText()
    println("Part 1: ${BridgeBuilder(input).getStrongestBridgeStrength()}")
    println("Part 1: ${BridgeBuilder(input).getLongestStrongestBridgeStrength()}")
}