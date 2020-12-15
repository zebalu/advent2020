package io.github.zebalu.advent2020

class MemoryGame(initTurns: List<Int>) {
	private val turns = initTurns.toMutableList()


	fun findNth(n: Int): Int {
		while (turns.size < n) {
			next()
		}
		return turns[n - 1]
	}

	private fun next() {
		val last = turns.last()
		val lastIndex = lastButOneIndexOf(last)
		if (lastIndex < 0) {
			turns.add(0)
		} else {
			turns.add(turns.size - lastIndex - 1)
		}
	}

	private fun lastButOneIndexOf(num: Int): Int {
		var index = turns.size - 2
		while (index >= 0 && turns[index] != num) {
			--index
		}
		return index
	}
}