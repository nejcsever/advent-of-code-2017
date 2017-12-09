package day7

object ProgramTowers {
    fun getRootProgramName(input: String): String {
        return buildTower(input).name
    }

    fun getCorrectTowerWeight(input: String): Int {
        val rootProgram = buildTower(input)
        return calculateTowerWeights(rootProgram)!!
    }

    private fun buildTower(input: String): Program {
        val allPrograms = input.split("\n")
                .map { it.split(" ") }
                .map { Program(it[0], it[1].substring(1, it[1].length - 1).toInt()) }
                .map { Pair(it.name, it) }
                .toMap()
        val parents = input.split("\n")
                .map { it.split(" -> ") }
                .filter { it.size > 1 }
                .map { Pair(it[0].split(" ")[0], it[1].split(", ")) }

        for ((parentName, subPrograms) in parents) {
            allPrograms[parentName]!!.subPrograms = subPrograms.map { allPrograms[it]!! }
            allPrograms[parentName]!!.subPrograms!!.map { it.parent = allPrograms[parentName]!! }
        }

        var root = allPrograms.values.first()
        while (root.parent != null) {
            root = root.parent!!
        }
        return root
    }

    fun calculateTowerWeights(program: Program): Int? {
        if (program.subPrograms == null) {
            program.towerWeight = program.weight
            return null
        }
        program.subPrograms!!
                .mapNotNull { calculateTowerWeights(it) }
                .forEach { return it }

        val subTowerWeights = program.subPrograms!!.map { it.towerWeight }
        program.towerWeight = subTowerWeights.sum() + program.weight
        val isBalanced = subTowerWeights.toSet().size == 1
        if (!isBalanced) {
            val subTowerWeightMap = subTowerWeights.groupingBy { it }.eachCount()
            val minOccurrenceValue = subTowerWeightMap.minBy { it.value }!!.key
            val maxOccurrenceValue = subTowerWeightMap.maxBy { it.value }!!.key
            val unbalancedProgram = program.subPrograms!!.find { it.towerWeight == minOccurrenceValue }!!
            return unbalancedProgram.weight + (maxOccurrenceValue - minOccurrenceValue)
        }
        return null
    }

    class Program(var name: String, var weight: Int) {
        var parent: Program? = null
        var subPrograms: List<Program>? = null
        var towerWeight: Int = 0
    }
}