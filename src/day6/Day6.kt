package day6

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day6/input.txt").readText()
    val (cyclesCount, sizeOfTheLoop) = MemoryReallocator.reallocate(input)
    println("Part 1: $cyclesCount")
    println("Part 2: $sizeOfTheLoop")
}