package day6

object MemoryReallocator {
    fun reallocate(input: String): Pair<Int, Int> {
        var cells = input.split("\t").map { it.toInt() }.toMutableList()
        val seenCombinations = mutableSetOf<List<Int>>()
        var cyclesCount = 0
        while (!seenCombinations.contains(cells)) {
            cyclesCount++
            seenCombinations.add(cells.toList())
            val (maxValue, maxIndex) = maxValueWithIndex(cells)
            cells[maxIndex] = 0
            for (i in 1..maxValue) {
                cells[(maxIndex + i) % cells.size]++
            }
        }
        return Pair(cyclesCount, cyclesCount - seenCombinations.indexOf(cells))
    }

    private fun maxValueWithIndex(list: List<Int>): Pair<Int, Int> {
        val maxIndex = list.indices.maxBy { list[it] } ?: 0
        return Pair(list[maxIndex], maxIndex)
    }
}