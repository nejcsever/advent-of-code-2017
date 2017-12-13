package day13

class Firewall(input: String) {
    private val scanners: List<Scanner>

    init {
        scanners = input.split("\n")
                .map { it.split(": ") }
                .map { Scanner(it[0].toInt(), it[1].toInt()) }
    }

    fun getSeverity(): Int {
        return scanners.filter { it.isCaught(it.depth) }
                .fold(0, { severity, scanner -> severity + scanner.severity })
    }

    fun getUncaughtDelay(): Int {
        tailrec fun getDelay(delay: Int, firewall: List<Scanner>): Int {
            if (firewall.none { it.isCaught(it.depth + delay) }) {
                return delay
            }
            return getDelay(delay + 1, firewall)
        }
        return getDelay(0, scanners)
    }

    private class Scanner(val depth: Int, range: Int) {
        val severity: Int = depth * range
        private val movesInOneCycle: Int = 2 + 2 * (range - 2)

        fun isCaught(time: Int): Boolean {
            return time % movesInOneCycle == 0
        }
    }
}