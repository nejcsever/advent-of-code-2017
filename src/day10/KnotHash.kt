package day10

object KnotHash {
    fun sparseHashCode(input: String): Int {
        val lengths = input.split(",").map { it.toInt() }
        var currentPosition = 0
        val sparseHash = (0..255).map { it }.toMutableList()

        for ((skipSize, length) in lengths.withIndex()) {
            val swapRange = length / 2 - 1
            for (i in 0..swapRange) {
                val x = (currentPosition + i) % sparseHash.size
                val y = ((currentPosition + length - i) - 1) % sparseHash.size
                val temp = sparseHash[x]
                sparseHash[x] = sparseHash[y]
                sparseHash[y] = temp
            }
            currentPosition = (currentPosition + skipSize + length) % sparseHash.size
        }
        return sparseHash[0] * sparseHash[1]
    }

    fun digest(input: String): String {
        val lengths = input.map { it.toInt() } + listOf(17, 31, 73, 47, 23)
        var currentPosition = 0
        var skipSize = 0
        val sparseHash = (0..255).map { it }.toMutableList()

        for (round in 1..64) {
            for (length in lengths) {
                val swapRange = length / 2 - 1
                for (i in 0..swapRange) {
                    val x = (currentPosition + i) % sparseHash.size
                    val y = ((currentPosition + length - i) - 1) % sparseHash.size
                    val temp = sparseHash[x]
                    sparseHash[x] = sparseHash[y]
                    sparseHash[y] = temp
                }
                currentPosition = (currentPosition + skipSize + length) % sparseHash.size
                skipSize++
            }
        }
        val denseHash = calculateDenseHash(sparseHash)
        return toHex(denseHash)
    }

    private fun calculateDenseHash(sparseHash: List<Int>): List<Int> {
        return (0 until (sparseHash.size - 1) step 16).map {
            var xorValue = sparseHash[it]
            for (j in 1..15) {
                xorValue = xorValue.xor(sparseHash[it + j])
            }
            xorValue
        }
    }

    private fun toHex(integers: List<Int>): String {
        return integers.map { String.format("%02X", it) }
                .joinToString(separator = "")
    }
}