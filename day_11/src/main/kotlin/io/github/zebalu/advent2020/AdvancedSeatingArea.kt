package io.github.zebalu.advent2020

class AdvancedSeatingArea {
	private val area: Array<CharArray>;

	constructor(lines: List<String>) {
		area = Array(lines.size) { i ->
			lines[i].toCharArray()
		}
	}

	fun countStableState(): Int {
		var stable = false
		while (!stable) {
			var next = calcNext()
			stable = !next.second
			if (!stable) {
				next.first.copyInto(area)
			}
		}
		return area.flatMap { sa -> sa.asSequence() }.count { c -> c == '#' }
	}

	private fun calcNext(): Pair<Array<CharArray>, Boolean> {
		val copy = Array(area.size) { i ->
			area[i].copyOf()
		}
		var changed = false
		for (i in copy.indices) {
			for (j in copy[i].indices) {
				val future = future(i, j)
				changed = changed || future != copy[i][j]
				copy[i][j] = future
			}
		}
		return Pair(copy, changed)
	}

	private fun future(i: Int, j: Int): Char {
		if (area[i][j] == '.') {
			return '.'
		} else if (area[i][j] == '#') {
			if (collectAdjecentSeats(i, j).count { c -> c == '#' } >= 5) {
				return 'L'
			} else {
				return '#'
			}
		} else if (area[i][j] == 'L') {
			if (collectAdjecentSeats(i, j).count { c -> c == '#' } == 0) {
				return '#'
			} else {
				return 'L'
			}
		} else {
			throw IllegalStateException("could not do anything with (" + i + ", " + j + ")")
		}
	}

	private fun collectAdjecentSeats(i: Int, j: Int): List<Char> {
		val result = mutableListOf<Char>()
		result.add(findFirstNotflore(i, j, -1, -1))
		result.add(findFirstNotflore(i, j, -1, +0))
		result.add(findFirstNotflore(i, j, -1, +1))
		result.add(findFirstNotflore(i, j, +0, -1))
		result.add(findFirstNotflore(i, j, +0, +1))
		result.add(findFirstNotflore(i, j, +1, -1))
		result.add(findFirstNotflore(i, j, +1, 0))
		result.add(findFirstNotflore(i, j, +1, +1))
		return result
	}

	private fun findFirstNotflore(i: Int, j: Int, iDiff: Int, jDiff: Int): Char {
		var ii = i + iDiff
		var jj = j + jDiff
		while (ii >= 0 && jj >= 0 && ii < area.size && jj < area[ii].size && area[ii][jj] == '.') {
			ii += iDiff
			jj += jDiff
		}
		if (ii >= 0 && jj >= 0 && ii < area.size && jj < area[ii].size) {
			return area[ii][jj]
		} else {
			return '.'
		}
	}
}