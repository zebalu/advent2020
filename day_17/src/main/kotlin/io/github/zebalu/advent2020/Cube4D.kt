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

class Cube4D(lines: List<String>) {
	var cubes =
		lines.indices.map { y -> lines[y].indices.map { x -> Coordinate4D(x, y, 0, 0) to lines[y][x] } }.flatten()
			.filter { it.second == '#' }.toMap()

	fun countActive() = cubes.size

	fun iterate() {
		cubes = cubes.keys.map { it.neighbours() }.flatten().toSet().map {
			calcActivity(it, valueAt(it), it.neighbours().count { n -> cubes.contains(n) })
		}.filter { it.second == '#' }.toMap()
	}

	private fun valueAt(coord: Coordinate4D) = cubes[coord] ?: '.'

	private fun calcActivity(coord: Coordinate4D, current: Char, activeNeighborCount: Int) =
		if (current == '.' && activeNeighborCount == 3) Pair(coord, '#')
		else if (current == '#' && (activeNeighborCount == 2 || activeNeighborCount == 3)) Pair(coord, '#')
		else Pair(coord, '.')

}
