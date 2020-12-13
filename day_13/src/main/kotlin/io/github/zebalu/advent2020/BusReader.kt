package io.github.zebalu.advent2020

object BusReader {

	fun readLines(lines: List<String>): BusFinder {
		val regex = Regex("(\\d+)")
		val now = lines[0].toInt()
		val buses = regex.findAll(lines[1]).map { it.groupValues[1] }.map { it.toInt() }.toList()
		return BusFinder(now, buses)
	}

	fun readBusRequest(lines: List<String>): List<Pair<Int, Int>> {
		val result = mutableListOf<Pair<Int, Int>>()
		lines[1].split(",").forEachIndexed { index, string ->
			if (isNumber(string)) {
				//println("$string \t -- \t $index \t --> \t ${getFrequencyModuloPair(string.toInt(), index)}")
				result += getFrequencyModuloPair(string.toInt(), index)
			}
		}
		return result
	}

	private fun isNumber(string: String): Boolean {
		try {
			string.toInt()
			return true
		} catch (e: Exception) {
			return false
		}
	}

	private fun getFrequencyModuloPair(frequency: Int, arrivesAt: Int): Pair<Int, Int> =
		if (arrivesAt == 0) Pair(frequency, arrivesAt)
		else if (arrivesAt < frequency) Pair(frequency, frequency - arrivesAt)
		else Pair(frequency, frequency - (arrivesAt % frequency))


}