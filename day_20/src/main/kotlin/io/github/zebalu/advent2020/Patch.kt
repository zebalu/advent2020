package io.github.zebalu.advent2020

class Patch(lines: List<String>) {
	val patch = lines.mapIndexed { y, line -> line.mapIndexed { x, c -> Pair(x, y) to c } }.flatten()
		.filter { it.second == '#' }.toMap()
	val width = patch.keys.map { it.first }.maxOrNull()!!
	val height = patch.keys.map { it.second }.maxOrNull()!!

	fun countRoughOnTile(tile: Tile) =
		tile.allPatches(width, height).map { countRoughOnMap(it) }.sum()
	
	fun countSeaMonsterAreas(tile:Tile) =
		tile.allPatches(width, height).map {countSeaMonstersOnMap(it)}.sum()

	private fun countRoughOnMap(map: Map<Pair<Int, Int>, Char>) =
		if (patch.keys.all { map[it] == patch[it] }) (map.keys - patch.keys).map { map[it] }.filter { it == '#' }
			.count()
		else 0
	
	private fun countSeaMonstersOnMap(map: Map<Pair<Int, Int>, Char>) =
		if (patch.keys.all { map[it] == patch[it] }) patch.size
		else 0
}