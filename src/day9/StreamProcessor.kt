package day9

object StreamProcessor {
    fun calculateGroupScoreAndGarbage(group: String): Pair<Int, Int> {
        var garbageMode = false
        var score = 0
        var garbage = 0
        var depth = 1
        var i = 0
        while (i < group.length) {
            val char = group[i]
            if (garbageMode) {
                when (char) {
                    '!' -> i++
                    '>' -> garbageMode = false
                    else -> garbage++
                }
            } else {
                when (char) {
                    '{' -> score += depth++
                    '}' -> depth--
                    '<' -> garbageMode = true
                }
            }
            i++
        }
        return Pair(score, garbage)
    }
}