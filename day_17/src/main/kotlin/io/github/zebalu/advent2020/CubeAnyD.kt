package io.github.zebalu.advent2020

data class Coord(val coords: IntArray) {

	fun neighbours() = cluster(1).drop(1)

	// original idea: https://github.com/p-kovacs/aoc2020/blob/master/src/main/java/pkovacs/aoc/util/Point.java
	fun cluster(size: Int): List<Coord> {
		if (size < 0) {
			throw IllegalArgumentException("cluster size can not be negative")
		}
		val list = mutableListOf(this)
		if (size == 0) {
			return list
		}
		for (coordId in coords.indices) {
			val range = 0..list.size - 1
			for (changeIdx in range) {
				for (diff in 1..size) {
					val positive = list[changeIdx].coords.copyOf()
					val negative = list[changeIdx].coords.copyOf()
					positive[coordId] += diff
					negative[coordId] -= diff
					list.add(Coord(positive))
					list.add(Coord(negative))
				}
			}

		}
		return list
	}

	override fun hashCode() = coords.map { 31 * it }.fold(coords.size) { acc, next -> acc * 121 + next }
	override fun equals(other: Any?) =
		if (other == null) false
		else if (other is Coord) coords.size == other.coords.size && coords.indices.all { coords[it] == other.coords[it] }
		else false


}

abstract class CubeAnyD {
	var cubes = setOf<Coord>()

	constructor(lines: List<String>, dimension: Int) {
		if (dimension < 2) {
			throw IllegalArgumentException("dimension must be at least 2, but was: $dimension")
		}
		cubes = lines.indices.map { y ->
			lines[y].indices.map { x ->
				if (lines[y][x] == '#')
					Coord(
						IntArray(dimension) { i ->
							when (i) {
								0 -> x
								1 -> y
								else -> 0
							}
						}
					) else Coord(IntArray(0))
			}.filter { it.coords.size > 0 }
		}.flatten().toSet()
	}

	fun countActive() = cubes.size

	fun iterate() {
		cubes = cubes.map { it.neighbours() }.flatten().toSet().map {
			calcActivity(it, valueAt(it), it.neighbours().count { n -> cubes.contains(n) })
		}.filter { it.second == '#' }.map { it.first }.toSet()
	}

	private fun valueAt(coord: Coord) = if (cubes.contains(coord)) '#' else '.'

	private fun calcActivity(coord: Coord, current: Char, activeNeighborCount: Int) =
		if (current == '.' && activeNeighborCount == 3) Pair(coord, '#')
		else if (current == '#' && (activeNeighborCount == 2 || activeNeighborCount == 3)) Pair(coord, '#')
		else Pair(coord, '.')


	private fun countNeighbours(coord: Coord) = coord.neighbours().count { cubes.contains(it) }
}