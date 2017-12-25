package day20

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day20/input.txt").readText()
    println("Part 1: ${Particles(input).getClosestParticleInTheLongTerm()}")
    println("Part 2: ${Particles(input).getNonCollidedParticlesCount(200_000)}")
}