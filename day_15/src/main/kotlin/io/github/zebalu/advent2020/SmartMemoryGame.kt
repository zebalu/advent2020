package io.github.zebalu.advent2020

class SmartMemoryGame(initTurns: List<Int>) {
	private val turns = initTurns.toMutableList()
	private val lastIndexes = mutableMapOf<Int, Int>()

	init {
		for (i in turns.indices) {
			lastIndexes[turns[i]] = i + 1
		}
	}

	fun findNth(n: Int): Int {
		while (turns.size < n) {
			next()
		}
		println(lastIndexes.size)
		return turns[n - 1]
	}

	private fun next() {
		val last = turns.last()
		val lastIndex = lastIndexes[last] ?: 0
		if (lastIndex == 0) {
			turns.add(0)
		} else {
			turns.add(turns.size - lastIndex)
		}
		lastIndexes[last] = turns.size - 1
	}
}