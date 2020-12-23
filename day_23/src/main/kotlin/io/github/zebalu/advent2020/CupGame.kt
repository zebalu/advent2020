package io.github.zebalu.advent2020

class CupGame(cupList: String) {
	val cups = cupList.map { "$it".toInt() }.toMutableList()
	val wrapNum = cups.maxOrNull()!! + 1
	var currentCup = 0

	fun shuffle(times: Int): String {
		repeat(times) {
			val pickedUp = pickUp3Cups()
			val nextDest = chooseDestinationCup()
			insertCups(nextDest.first, pickedUp)
			nextCup()
		}
		val indexOfOne = cups.indexOf(1)
		var nextId = (indexOfOne + 1) % cups.size
		var collector = ""

		while (nextId != indexOfOne) {
			collector += cups[nextId]
			nextId = (nextId + 1) % cups.size
		}
		return collector
	}

	private fun pickUp3Cups(): List<Int> {
		val result = mutableListOf<Int>()
		val cCup = cups[currentCup]
		result.add(cups[(currentCup + 1) % cups.size])
		result.add(cups[(currentCup + 2) % cups.size])
		result.add(cups[(currentCup + 3) % cups.size])
		cups.removeAll(result)
		currentCup = cups.indexOf(cCup)
		return result
	}

	private fun chooseDestinationCup(): Pair<Int, Int> {
		val cCup = cups[currentCup]
		var dest = (cCup - 1 + wrapNum) % wrapNum
		while (!cups.contains(dest)) {
			dest = (dest - 1 + wrapNum) % wrapNum
		}
		return Pair(cups.indexOf(dest), dest)
	}

	private fun insertCups(afterIdx: Int, cupsToInsert: List<Int>) {
		val cCup = cups[currentCup]
		if (afterIdx == cups.size - 1) {
			cups.addAll(cupsToInsert)
		} else {
			cups.addAll((afterIdx + 1) % cups.size, cupsToInsert)
		}
		currentCup = cups.indexOf(cCup)
	}

	private fun nextCup() {
		currentCup = (currentCup + 1) % cups.size
	}

	private fun toGameString() =
		cups.mapIndexed { i, v -> if (i == currentCup) "($v)" else "$v" }.reduce { a, n -> "$a, $n" }
}