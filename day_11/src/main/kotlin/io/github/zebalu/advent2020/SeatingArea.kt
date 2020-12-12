package io.github.zebalu.advent2020

import java.lang.IllegalStateException

class SeatingArea {

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
		} else if (area[i][j] == '#' && countAdjecentOccupiedSeats(i, j) >= 4) {
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
		for (ii in i - 1..i + 1) {
			for (jj in j - 1..j + 1) {
				if ((ii != i || jj != j) && (ii > -1 && jj > -1 && ii < area.size && jj < area[ii].size)) {
					result.add(area[ii][jj])
				}
			}
		}
		return result
	}
}