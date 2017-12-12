package day11

object HexGrid {
    fun getNoOfSteps(input: String): Int {
        val directions = input.split(",")
        val endPoint = directions.fold(Position(0, 0, 0), { pos, direction -> pos.move(direction) })
        return endPoint.distanceFromCenter()
    }

    fun getMaxDistance(input: String): Int {
        val directions = input.split(",")
        var pos = Position(0, 0, 0)
        return directions.map { pos = pos.move(it); pos }
                .map { it.distanceFromCenter() }
                .max()!!
    }

    private class Position(val x: Int, val y: Int, val z: Int) {
        fun distanceFromCenter(): Int {
            return (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2
        }

        fun move(direction: String): Position {
            when (direction) {
                "nw" -> return Position(x - 1, y + 1, z)
                "n" -> return Position(x, y + 1, z - 1)
                "ne" -> return Position(x + 1, y, z - 1)
                "se" -> return Position(x + 1, y - 1, z)
                "s" -> return Position(x, y - 1, z + 1)
                "sw" -> return Position(x - 1, y, z + 1)
                else -> return this
            }
        }
    }
}