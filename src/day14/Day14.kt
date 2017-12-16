package day14

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day14/input.txt").readText()
    println("Part 1: ${DiskDefragmenter(input).squaresCount()}")
    println("Part 2: ${DiskDefragmenter(input).diskRegionsCount()}")
}

