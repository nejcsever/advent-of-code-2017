package day1

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day1/input.txt").readText()
    println("Part 1: ${InverseCaptcha.solve(input, 1)}")
    println("Part 2: ${InverseCaptcha.solve(input, input.length/2)}")
}
