package day21

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day21/input.txt").readText()
    val startingPixels = ".#./..#/###"
    println("Part 1: ${Screen(startingPixels, input).getLitPixels(5)}")
    println("Part 2: ${Screen(startingPixels, input).getLitPixels(18)}")
}