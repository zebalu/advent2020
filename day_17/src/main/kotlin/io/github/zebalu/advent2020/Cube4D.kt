package io.github.zebalu.advent2020

data class Coordinate4D(val x: Int, val y: Int, val z: Int, val w: Int) {
	fun neighbours() =
		(-1..1).map { xd ->
			(-1..1).map { yd ->
				(-1..1).map { zd ->
					(-1..1).map { wd ->
						Coordinate4D(x + xd, y + yd, z + zd, w + wd)
					}
				}.flatten()
			}.flatten()
		}.flatten().filter { it != this }
}

class Cube4D {
	val cubes = mutableMapOf<Coordinate4D, Char>()

	constructor(lines: List<String>) {
		for (y in lines.indices) {
			for (x in lines[y].indices) {
				if (lines[y][x] == '#') {
					cubes[Coordinate4D(x, y, 0, 0)] = '#'
				}
			}
		}
	}

	fun countActive() = cubes.size

	fun iterate() {
		val nextPhase = cubes.keys.map { it.neighbours() }.flatten().toSet().map { c ->
			val count = c.neighbours().count { valueAt(it) == '#' }
			val valueAt = valueAt(c)
			if (valueAt == '.' && count == 3) Pair(c, '#')
			else if (valueAt == '#' && (count == 2 || count == 3)) Pair(c, '#')
			else Pair(c, '.')
		}.filter { it.second == '#' }.map { it.first to it.second }.toMap()
		cubes.clear()
		cubes += nextPhase
	}

	private fun valueAt(coord: Coordinate4D) = cubes[coord] ?: '.'
}
