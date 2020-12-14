package io.github.zebalu.advent2020

object BusReader {

	fun readLines(lines: List<String>): BusFinder {
		val regex = Regex("(\\d+)")
		val now = lines[0].toInt()
		val buses = regex.findAll(lines[1]).map { it.groupValues[1] }.map { it.toInt() }.toList()
		return BusFinder(now, buses)
	}

	fun readBusRequest(lines: List<String>): List<Pair<Long, Long>> {
		val result = mutableListOf<Pair<Long, Long>>()
		lines[1].split(",").forEachIndexed { index, string ->
			if (isNumber(string)) {
				result += getFrequencyModuloPair(string.toLong(), index.toLong())
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

	private fun getFrequencyModuloPair(frequency: Long, arrivesAt: Long): Pair<Long, Long> =
		if (arrivesAt == 0L) Pair(frequency, arrivesAt)
		else if (arrivesAt < frequency) Pair(frequency, frequency - arrivesAt)
		else Pair(frequency, frequency - (arrivesAt % frequency))


}