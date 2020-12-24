package io.github.zebalu.advent2020

data class Coord(val x: Int, val y: Int) {
	fun adjectens(): List<Coord> {
		val result = mutableListOf<Coord>()
		result += Coord(x - 2, y)
		result += Coord(x + 2, y)
		result += Coord(x - 1, y - 1)
		result += Coord(x - 1, y + 1)
		result += Coord(x + 1, y - 1)
		result += Coord(x + 1, y + 1)
		return result
	}
}

class TileFlipper(lines: List<String>) {
	var flipped = mutableSetOf<Coord>()

	init {
		lines.map { lineToCoord(it) }.forEach { coord ->
			if (flipped.contains(coord)) {
				flipped.remove(coord)
			} else {
				flipped.add(coord)
			}
		}
	}

	fun countTiles() = flipped.size
	fun flip(days: Int) {
		repeat(days) {
			flipped = allInterestingTiles().map { it to Pair(flipped.contains(it), countBlackNeighbours(it)) }
				.filter { it.second.first && it.second.second in 1..2 || !it.second.first && it.second.second == 2 }
				.map { it.first }.toMutableSet()
		}
	}

	private fun countBlackNeighbours(coord: Coord) = coord.adjectens().filter { flipped.contains(it) }.count()

	fun allInterestingTiles() = flipped.map { it.adjectens() + it }.flatten().toSet()

	private fun lineToCoord(line: String): Coord {
		var x = 0
		var y = 0
		var i = 0
		while (i < line.length) {
			when (line[i]) {
				'e' -> x -= 2
				'w' -> x += 2
				'n', 's' -> {
					y += halfStepY(line[i])
					++i
					x += halfStepX(line[i])
				}
			}
			++i
		}
		return Coord(x, y)
	}

	private fun halfStepX(c: Char): Int = when (c) {
		'e' -> -1
		'w' -> +1
		else -> throw IllegalArgumentException("don't recognise: $c")
	}

	private fun halfStepY(c: Char): Int = when (c) {
		'n' -> +1
		's' -> -1
		else -> throw IllegalArgumentException("don't recognise: $c")
	}
}