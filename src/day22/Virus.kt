package day22

class Virus(input: String) {
    private val infectedNodes = input.split("\n")
            .mapIndexed { y, line ->
                line.mapIndexed { x, node -> Pair(XY(x, y), node) }
                        .filter { it.second == '#' }
            }.flatten()
            .toMap()
    private val startingPosition = XY(input.split("\n").size / 2, input.split("\n").size / 2)
    private val startingDirection = Direction.UP

    fun infectionCountPart1(noOfBursts: Int): Int {
        val infected = infectedNodes

        tailrec fun countInfections(infectionCount: Int, burstSize: Int, infectedNodes: Map<XY, Char>, pos: XY, direction: Direction): Int {
            if (burstSize == 0) {
                return infectionCount
            }
            val isInfected = infectedNodes.containsKey(pos)
            val newDirection = if (isInfected) direction.turnRight() else direction.turnLeft()
            val newInfections = if (isInfected) infectedNodes - pos else infectedNodes + Pair(pos, '#')
            val infectionIncrease = if (isInfected) 0 else 1
            return countInfections(infectionCount + infectionIncrease, burstSize - 1, newInfections, pos.move(newDirection), newDirection)
        }
        return countInfections(0, noOfBursts, infected, startingPosition, startingDirection)
    }

    fun infectionCountPart2(noOfBursts: Int): Int {
        var position = startingPosition
        var direction = startingDirection
        val nodes = infectedNodes.toMutableMap()
        var infectionsCount = 0
        for (i in 1..noOfBursts) {
            val currentNodeValue = nodes[position] ?: '.'
            direction = when (currentNodeValue) {
                '.' -> direction.turnLeft()
                '#' -> direction.turnRight()
                'F' -> direction.reverse()
                else -> direction
            }
            val newNodeValue = when (currentNodeValue) {
                '#' -> 'F'
                'F' -> '.'
                'W' -> '#'
                else -> 'W'
            }
            if (newNodeValue == '.') {
                nodes.remove(position)
            } else {
                nodes.put(position, newNodeValue)
            }
            if (newNodeValue == '#') {
                infectionsCount++
            }
            position = position.move(direction)
        }
        return infectionsCount

    }

    data class XY(val x: Int, val y: Int) {
        fun move(direction: Direction): XY = XY(x + direction.x, y + direction.y)
    }

    enum class Direction(val x: Int, val y: Int) {
        RIGHT(1, 0),
        UP(0, -1),
        LEFT(-1, 0),
        DOWN(0, 1);

        fun turnLeft(): Direction =
                when (this) {
                    UP -> LEFT
                    DOWN -> RIGHT
                    LEFT -> DOWN
                    RIGHT -> UP
                }

        fun turnRight(): Direction =
                when (this) {
                    UP -> RIGHT
                    DOWN -> LEFT
                    LEFT -> UP
                    RIGHT -> DOWN
                }

        fun reverse(): Direction =
                when (this) {
                    UP -> DOWN
                    DOWN -> UP
                    LEFT -> RIGHT
                    RIGHT -> LEFT
                }
    }
}