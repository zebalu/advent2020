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
		return if (area[i][j] == '.') {
			'.'
		} else if (area[i][j] == '#' && countAdjecentOccupiedSeats(i, j) >= 5) {
			'L'
		} else if (area[i][j] == 'L' && countAdjecentOccupiedSeats(i, j) == 0) {
			'#'
		} else {
			area[i][j]
		}
	}

	private fun countAdjecentOccupiedSeats(i: Int, j: Int) = collectAdjecentSeats(i, j).count { c -> c == '#' }

	private fun collectAdjecentSeats(i: Int, j: Int): List<Char> {
		val result = mutableListOf<Char>()
		for (iDiff in -1..1) {
			for (jDiff in -1..1) {
				if (!(iDiff == jDiff && iDiff == 0)) {
					result.add(findFirstNotflore(i, j, iDiff, jDiff))
				}
			}
		}
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