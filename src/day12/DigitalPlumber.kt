package day12

object DigitalPlumber {
    fun getElementsInAZeroGroup(input: String): Int {
        val graph = buildGraph(input)
        return getAllElementsInAGroup(graph, 0).size
    }

    fun getNoOfAllGroups(input: String): Int {
        var i: Int? = 0
        val graph = buildGraph(input)
        var visited = setOf<Int>()
        var noOfGroups = 0
        while (i != null) {
            noOfGroups++
            visited = visited.union(getAllElementsInAGroup(graph, i))
            i = graph.keys.find { !visited.contains(it) }
        }
        return noOfGroups
    }

    private fun buildGraph(input: String): Map<Int, List<Int>> {
        return input.split("\n").map {
            val chunks = it.split(" <-> ")
            Pair(chunks[0].toInt(), chunks[1].split(", ").map { it.toInt() })
        }.toMap()
    }

    private fun getAllElementsInAGroup(graph: Map<Int, List<Int>>, group: Int): Set<Int> {
        val visited = mutableSetOf<Int>()
        fun noOfElements(graph: Map<Int, List<Int>>, i: Int) {
            if (visited.contains(i)) {
                return
            }
            visited.add(i)
            graph[i]!!.map { noOfElements(graph, it) }
        }
        noOfElements(graph, group)
        return visited.toSet()
    }
}