package day21

import java.lang.Math.pow
import java.lang.Math.sqrt

class Screen(val startingPixels: String, input: String) {
    val rules = parseRules(input)

    private fun parseRules(input: String): Map<String, String> {
        fun scramble(string: String, indices: List<Int>): String = indices.map { string[it] }.joinToString(separator = "")
        val doubleCombinations = listOf(
                listOf(0, 1, 2, 3, 4),
                listOf(1, 4, 2, 0, 3),
                listOf(4, 3, 2, 1, 0),
                listOf(3, 0, 2, 4, 1),
                listOf(1, 0, 2, 4, 3),
                listOf(0, 3, 2, 1, 4),
                listOf(3, 4, 2, 0, 1),
                listOf(4, 1, 2, 3, 0)
        )
        val tripleCombinations = listOf(
                listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                listOf(2, 6, 10, 3, 1, 5, 9, 7, 0, 4, 8),
                listOf(10, 9, 8, 3, 6, 5, 4, 7, 2, 1, 0),
                listOf(8, 4, 0, 3, 9, 5, 1, 7, 10, 6, 2),
                listOf(2, 1, 0, 3, 6, 5, 4, 7, 10, 9, 8),
                listOf(0, 4, 8, 3, 1, 5, 9, 7, 2, 6, 10),
                listOf(8, 9, 10, 3, 4, 5, 6, 7, 0, 1, 2),
                listOf(10, 6, 2, 3, 9, 5, 1, 7, 8, 4, 0)
        )
        return input.split("\n")
                .map { it.split(" => ") }
                .map { ruleSplit ->
                    val combinations = if (ruleSplit[0].length < 6) doubleCombinations else tripleCombinations
                    combinations.map { comb -> Pair(scramble(ruleSplit[0], comb), ruleSplit[1]) }
                }.flatten()
                .toMap()
    }

    fun getLitPixels(iterations: Int): Int =
            (0 until iterations)
                    .fold(startingPixels, { pixels, _ ->
                        val smallSquares = splitToSmallerSquares(pixels)
                        val extendedSquares = applyRules(smallSquares)
                        joinSquares(extendedSquares)
                    }).count { it == '#' }

    private fun applyRules(smallSquares: List<String>): List<String> = smallSquares.map { rules[it]!! }

    private fun joinSquares(squares: List<String>): String {
        if (squares.size < 2) {
            return squares[0]
        }
        val axisSize = sqrt(squares.size.toDouble()).toInt()
        val smallSquareAxisSize = squares[0].split("/").size
        return (0 until squares.size step axisSize).map { axisOffset ->
            (0 until smallSquareAxisSize).map { lineIndex ->
                (0 until axisSize).map { squareIndex ->
                    squares[squareIndex + axisOffset].split("/")[lineIndex]
                }.joinToString(separator = "")
            }.joinToString(separator = "/")
        }.joinToString(separator = "/")
    }

    private fun splitToSmallerSquares(square: String): List<String> {
        val lines = square.split("/")
        val splitFactor = if (lines.size % 2 == 0) 2 else 3
        val noOfSmallSquares = pow(lines.size / splitFactor.toDouble(), 2.0).toInt()
        if (noOfSmallSquares == 1) {
            return listOf(square)
        }
        return (0 until lines.size step splitFactor).map { squareIndex ->
            (0 until lines.size step splitFactor).map { j ->
                (0 until splitFactor).map { i ->
                    lines[i + squareIndex].substring(j, j + splitFactor)
                }.joinToString(separator = "/")
            }
        }.flatten()
    }
}