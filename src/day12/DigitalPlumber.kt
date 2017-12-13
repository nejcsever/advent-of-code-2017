package day12

typealias Graph = Map<Int, List<Int>>

class DigitalPlumber(input: String) {
    val graph: Graph

    init {
        graph = input.split("\n").map {
            val chunks = it.split(" <-> ")
            Pair(chunks[0].toInt(), chunks[1].split(", ").map { it.toInt() })
        }.toMap()
    }

    fun getNoOfElementsForProgramZero(): Int {
        return getAllGroupElementsForId(graph, 0).size
    }

    fun getNoOfGroups(): Int {
        tailrec fun noOfGroups(groupCount: Int, visitedPrograms: Set<Int>, graph: Map<Int, List<Int>>): Int {
            val group = graph.keys.find { !visitedPrograms.contains(it) } ?: return groupCount
            return noOfGroups(groupCount + 1, visitedPrograms.union(getAllGroupElementsForId(graph, group)), graph)
        }
        return noOfGroups(0, setOf<Int>(), graph)
    }

    private fun getAllGroupElementsForId(graph: Map<Int, List<Int>>, programId: Int): Set<Int> {
        val visited = mutableSetOf<Int>()
        fun noOfElements(graph: Map<Int, List<Int>>, i: Int) {
            if (visited.contains(i)) {
                return
            }
            visited.add(i)
            graph[i]!!.map { noOfElements(graph, it) }
        }
        noOfElements(graph, programId)
        return visited.toSet()
    }
}