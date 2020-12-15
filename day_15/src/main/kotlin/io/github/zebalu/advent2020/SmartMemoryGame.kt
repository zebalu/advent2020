package io.github.zebalu.advent2020

class SmartMemoryGame(initTurns: List<Int>, size: Int) {
	private val turns = IntArray(size) { _ -> -1 }
	private val lastIndexes = IntArray(size) { _ -> -1 }

	private var steps = initTurns.size

	init {
		for (i in initTurns.indices) {
			turns[i] = initTurns[i]
			lastIndexes[turns[i]] = i + 1
		}
	}

	fun findNth(n: Int): Int {
		while (steps < n) {
			next()
		}
		return turns[n - 1]
	}

	private fun next() {
		val last = turns[steps - 1]
		val lastIndex = lastIndexes[last]
		if (lastIndex == -1) {
			turns[steps] = 0
		} else {
			turns[steps] = (steps - lastIndex)
		}
		lastIndexes[last] = steps
		++steps
	}
}