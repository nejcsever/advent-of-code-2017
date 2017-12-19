package day19

class Router(input: String) {
    private val graph = input.split("\n").mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            Pair(Position(x, y), char)
        }
    }
            .flatten()
            .filter { it.second != ' ' }
            .toMap()

    fun getLettersOnPath(): String {
        return getPath()
                .filter { !arrayOf(' ', '+', '|', '-').contains(it) }
                .joinToString(separator = "")
    }

    fun getNumberOfSteps(): Int {
        return getPath().count()
    }

    private fun getPath(): List<Char> {
        tailrec fun getPath(path: List<Char>, position: Position, direction: Direction): List<Char> {
            val currentChar = graph[position] ?: return path
            if (currentChar == '+') {
                val newDirection = getNewDirection(graph, position, direction)
                return getPath(path + currentChar, position.move(newDirection), newDirection)
            } else {
                return getPath(path + currentChar, position.move(direction), direction)
            }
        }

        val startingDirection = Direction.DOWN
        val startingPosition = graph.keys.first { it.y == 0 }
        return getPath(listOf(), startingPosition, startingDirection)
    }

    private fun getNewDirection(graph: Map<Position, Char>, position: Position, direction: Direction): Direction {
        if (direction == Direction.DOWN || direction == Direction.UP) {
            return if (graph.containsKey(position.move(Direction.LEFT))) Direction.LEFT else Direction.RIGHT
        } else {
            return if (graph.containsKey(position.move(Direction.UP))) Direction.UP else Direction.DOWN
        }
    }

    private data class Position(val x: Int, val y: Int) {
        fun move(direction: Direction): Position {
            return Position(x + direction.x, y + direction.y)
        }
    }

    private enum class Direction(val x: Int, val y: Int) {
        RIGHT(1, 0),
        UP(0, -1),
        LEFT(-1, 0),
        DOWN(0, 1);
    }
}