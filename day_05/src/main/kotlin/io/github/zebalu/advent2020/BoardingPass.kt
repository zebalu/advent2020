package io.github.zebalu.advent2020

class BoardingPass(code: String) : Comparable<BoardingPass> {
	private val row = findRow(code.substring(0, 7))
	private val col = findCol(code.substring(7, code.length))

	fun getId() = row * 8 + col

	override fun compareTo(other: BoardingPass) = getId() - other.getId()

	private fun findRow(code: String) = intervalHalfing(code, 0, 127)

	private fun findCol(code: String) = intervalHalfing(code, 0, 7)

	private fun intervalHalfing(code: String, start: Int, end: Int): Int {
		var lower = start
		var upper = end
		var middle = (lower + upper) / 2
		code.forEach { c ->
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