package day20

class Particles(input: String) {
    private val PARTICLE_REGEX = "p=<(.*?)>, v=<(.*?)>, a=<(.*?)>".toRegex()
    private val particles = input.split("\n")
            .mapIndexed { i, particleString ->
                val regexGroups = PARTICLE_REGEX.matchEntire(particleString)?.groups!!
                val pos = regexGroups[1]!!.value.split(",").map { it.toInt() }
                val velocity = regexGroups[2]!!.value.split(",").map { it.toInt() }
                val acc = regexGroups[3]!!.value.split(",").map { it.toInt() }
                Particle(i, XYZ(pos[0], pos[1], pos[2]),
                        XYZ(velocity[0], velocity[1], velocity[2]),
                        XYZ(acc[0], acc[1], acc[2]))
            }

    fun getClosestParticleInTheLongTerm(): Int = particles.minBy { it.acceleration() }!!.id

    fun getNonCollidedParticlesCount(iterations: Int): Int {
        val mutableParticles = particles.toMutableList()
        (1..iterations).forEach {
            val duplicatedPositions = mutableParticles.map { it.position }
                    .groupingBy { it }
                    .eachCount()
                    .filter { it.value > 1 }
            mutableParticles.removeAll { duplicatedPositions.contains(it.position) }
            mutableParticles.map { it.move() }
        }
        return mutableParticles.size
    }

    private class Particle(val id: Int, var position: XYZ, var velocity: XYZ, val acc: XYZ) {

        fun acceleration(): Int = Math.abs(acc.x) + Math.abs(acc.y) + Math.abs(acc.z)

        fun move() {
            velocity = XYZ(velocity.x + acc.x, velocity.y + acc.y, velocity.z + acc.z)
            position = XYZ(position.x + velocity.x, position.y + velocity.y, position.z + velocity.z)
        }
    }

    private data class XYZ(val x: Int, val y: Int, val z: Int)
}