package day6

object MemoryReallocator {
    fun reallocate(input: String): Pair<Int, Int> {
        var cells = input.split("\t").map { it.toInt() }.toMutableList()
        val seenCombinations = mutableMapOf<List<Int>, Int>()
        var cyclesCount = 0
        while (!seenCombinations.containsKey(cells)) {
            seenCombinations.put(cells.toList(), cyclesCount)
            cyclesCount++
            val (maxValue, maxIndex) = maxValueWithIndex(cells)
            cells[maxIndex] = 0
            for (i in 1..maxValue) {
                cells[(maxIndex + i) % cells.size]++
            }
        }
        return Pair(cyclesCount, cyclesCount - seenCombinations[cells]!!)
    }

    private fun maxValueWithIndex(list: List<Int>): Pair<Int, Int> {
        val maxIndex = list.indices.maxBy { list[it] } ?: 0
        return Pair(list[maxIndex], maxIndex)
    }
}