package day14

import day10.KnotHash

class DiskDefragmenter(input: String) {
    private val salt: String = input

    fun squaresCount(): Int {
        return (0..127)
                .map { KnotHash.digest("$salt-$it") }
                .map { hexRow -> hexToBinaryRow(hexRow) }
                .sumBy { binaryRow -> binaryRow.count { it == '1' } }
    }

    fun diskRegionsCount(): Int {
        val grid = (0..127)
                .map { KnotHash.digest("$salt-$it") }
                .map { hexRow -> hexToBinaryRow(hexRow) }
                .mapIndexed { x, binaryRow ->
                    binaryRow.mapIndexed { y, bit -> Pair(XY(x, y), bit) }
                            .filter { it.second == '1' }
                            .map { it.first }
                }
                .flatten()
                .toMutableSet()

        fun removeElementsOfGroup(xy: XY, grid: MutableSet<XY>) {
            if (!grid.remove(xy)) {
                return
            }
            removeElementsOfGroup(XY(xy.x - 1, xy.y), grid)
            removeElementsOfGroup(XY(xy.x + 1, xy.y), grid)
            removeElementsOfGroup(XY(xy.x, xy.y - 1), grid)
            removeElementsOfGroup(XY(xy.x, xy.y + 1), grid)
        }

        return 1 + generateSequence { removeElementsOfGroup(grid.first(), grid) }
                .takeWhile { !grid.isEmpty() }
                .count()
    }

    private fun hexToBinaryRow(hexRow: String): String {
        return hexRow.map { hexNumber ->
            String.format("%04d", Integer.valueOf(Integer.toBinaryString(Integer.parseInt(hexNumber.toString(), 16))))
        }.joinToString(separator = "")
    }

    private data class XY(val x: Int, val y: Int)
}