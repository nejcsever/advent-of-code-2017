package day3

object SpiralMemory {

    fun getManhattanDistance(input: Int): Int {
        var counter = 1
        var level = 0
        var position = Position(0, 0)
        var direction = Direction.RIGHT
        while (counter < input) {
            counter++
            // Are we on the next level?
            if (counter >= (2 * level + 1) * (2 * level + 1)) {
                level++
            }
            val newPos = position.move(direction)
            // Change direction if out of level bounds
            if (isOutOfBounds(newPos, level)) {
                direction = direction.rotateCounterClockwise()
            }
            position = position.move(direction)

        }
        return position.manhattanDist()
    }

    fun getStressTestData(input: Int): Int {
        var level = 0
        var position = Position(0, 0)
        var direction = Direction.RIGHT
        var memoryData = mutableMapOf(Pair(position, 1))

        var counter = 1
        while (true) {
            counter++
            // Are we on the next level?
            if (counter >= (2 * level + 1) * (2 * level + 1)) {
                level++
            }
            val newPos = position.move(direction)
            // Change direction if out of level bounds
            if (isOutOfBounds(newPos, level)) {
                direction = direction.rotateCounterClockwise()
            }
            position = position.move(direction)

            val currentValue = sumNeighbours(memoryData, position)
            if (currentValue > input) {
                return currentValue
            }
            memoryData[position] = currentValue
        }
    }

    private fun isOutOfBounds(pos: Position, level: Int): Boolean {
        return Math.abs(pos.x) > level || Math.abs(pos.y) > level
    }

    private fun sumNeighbours(memoryData: Map<Position, Int>, position: Position): Int {
        var sum = 0
        for (x in -1..1) {
            for (y in -1..1) {
                sum += memoryData[Position(position.x + x, position.y + y)] ?: 0
            }
        }
        return sum
    }

    private data class Position(val x: Int, val y: Int) {
        fun move(direction: Direction): Position {
            return Position(x + direction.x, y + direction.y)
        }

        fun manhattanDist(): Int {
            return Math.abs(x) + Math.abs(y)
        }
    }

    private enum class Direction(val x: Int, val y: Int) {
        RIGHT(1, 0),
        UP(0, 1),
        LEFT(-1, 0),
        DOWN(0, -1);

        fun rotateCounterClockwise(): Direction {
            when (this) {
                Direction.RIGHT -> return Direction.UP
                Direction.UP -> return Direction.LEFT
                Direction.LEFT -> return Direction.DOWN
                Direction.DOWN -> return Direction.RIGHT
            }
        }
    }
}