package day24

class BridgeBuilder(input: String) {
    private val adapters = input.split("\n")
            .map { it.split("/") }
            .map { Adapter(it[0].toInt(), it[1].toInt()) }

    fun getStrongestBridgeStrength(): Int {
        fun getStrongestBridge(port: Int, adapters: List<Adapter>): Int {
            val matchingAdapters = adapters.filter { it.port1 == port || it.port2 == port }
            if (matchingAdapters.isEmpty()) {
                return 0
            }
            return matchingAdapters.map {
                it.strength + getStrongestBridge(it.getUnusedPort(port), adapters - it)
            }.max()!!
        }
        return getStrongestBridge(0, adapters)
    }

    fun getLongestStrongestBridgeStrength(): Int {
        fun getStrongestLongestBridge(port: Int, adapters: List<Adapter>): BridgeSpecs {
            val matchingAdapters = adapters.filter { it.port1 == port || it.port2 == port }
            if (matchingAdapters.isEmpty()) {
                return BridgeSpecs(0, 0)
            }
            val subBridges = matchingAdapters.map { adapter ->
                val subBridge = getStrongestLongestBridge(adapter.getUnusedPort(port), adapters - adapter)
                BridgeSpecs(subBridge.strength + adapter.strength, subBridge.length + 1)
            }
            val longestLength = subBridges.maxBy { it.length }!!.length
            return subBridges.filter { it.length == longestLength }
                    .maxBy { it.strength }!!
        }
        return getStrongestLongestBridge(0, adapters).strength
    }

    private class Adapter(val port1: Int, val port2: Int) {
        val strength = port1 + port2
        fun getUnusedPort(usedPort: Int): Int = if (port1 == usedPort) port2 else port1
    }

    private class BridgeSpecs(val strength: Int, val length: Int)
}