package day13

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day13/input.txt").readText()
    println("Part 1: ${Firewall(input).getSeverity()}")
    println("Part 2: ${Firewall(input).getUncaughtDelay()}")
}