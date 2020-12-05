package io.github.zebalu.advent2020

object BoardingPassReader {

	fun getPasses(lines: List<String>): List<BoardingPass> {
		return lines.map { l -> BoardingPass(l) }
	}

	fun getHighestId(lines: List<String>): Int {
		return getPasses(lines).map { p -> p.getId() }.maxOrNull()!!
	}

	fun getMissingId(lines: List<String>): Int {
		val sortedPasses = getPasses(lines).sorted()
		var idx = 0
		var expected = sortedPasses[idx].getId()
		while (expected == sortedPasses[idx].getId()) {
			++expected
			++idx
		}
		return expected
	}

}