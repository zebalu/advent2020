package io.github.zebalu.advent2020

class BoardingPass : Comparable<BoardingPass> {
	private val row: Int;
	private val col: Int;

	constructor(code: String) {
		row = findRow(code.substring(0, 7))
		col = findCol(code.substring(7, code.length))
	}

	fun getId(): Int {
		return row * 8 + col
	}

	override fun compareTo(other: BoardingPass): Int {
		return getId() - other.getId()
	}

	private fun findRow(code: String): Int {
		return intervalHalfing(code, 0, 127)
	}

	private fun findCol(code: String): Int {
		return intervalHalfing(code, 0, 7)
	}

	private fun intervalHalfing(code: String, start: Int, end: Int): Int {
		var lower = start
		var upper = end
		var middle = (lower + upper) / 2
		for (c in code) {
			if (c == 'F' || c == 'L') {
				upper = middle
			} else {
				lower = middle + 1
			}
			middle = (lower + upper) / 2
		}
		return middle
	}
}