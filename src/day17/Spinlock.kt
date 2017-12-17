package day17

class CircularBuffer(input: String) {
    private val step: Int = input.toInt()

    fun getValueAfter(limit: Int): Int {
        tailrec fun getValueAfterLimit(buffer: List<Int>, position: Int, value: Int, limit: Int): Int {
            if (value > limit) {
                return buffer[position + 1]
            }
            val newPosition = (position + step) % buffer.size + 1
            val newBuffer = buffer.insertAtIndex(value, newPosition)
            return getValueAfterLimit(newBuffer, newPosition, value + 1, limit)
        }
        return getValueAfterLimit(listOf(0), 0, 1, limit)
    }

    fun getValueAfterZero(iterations: Int): Int {
        tailrec fun valueAfterZero(bufferSize: Int, position: Int, value: Int, iterations: Int, result: Int): Int {
            if (value > iterations) {
                return result
            }
            val newPosition = (position + step) % bufferSize + 1
            val newResult = if (newPosition == 1) value else result
            return valueAfterZero(bufferSize + 1, newPosition, value + 1, iterations, newResult)
        }
        return valueAfterZero(1, 0, 1, iterations, 1)
    }
}

private fun <E> List<E>.insertAtIndex(newValue: E, position: Int): List<E> {
    return this.take(position) + newValue + this.takeLast(size - position)
}
