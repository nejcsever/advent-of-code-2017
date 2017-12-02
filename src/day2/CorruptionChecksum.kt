package day2

object CorruptionChecksum {
    fun calculateSumMinMax(spreadsheet: List<List<Int>>): Int {
        return spreadsheet.sumBy { it.max()!! - it.min()!! }
    }

    fun calculateSumDivision(spreadsheet: List<List<Int>>): Int {
        tailrec fun calcDivision(num: Int, list: List<Int>): Int {
            val match = list.find { it % num == 0 || num % it == 0 } ?: return calcDivision(list[0], list.slice(1..list.size - 1))
            if (match > num) {
                return match / num
            }
            return num / match
        }
        return spreadsheet.sumBy { calcDivision(it[0], it.slice(1..it.size - 1)) }
    }
}