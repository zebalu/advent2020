package io.github.zebalu.advent2020

class SeeMap(mapStrings: List<List<String>>) {

	val tiles = mapStrings.map { Tile(it) }
	val tileEdges = tiles.map { it to it.getAllPossibleEdges() }.toMap()

	fun multiplyCornerids(): Long {
		return corners().map { it.getId() }.fold(1L) { acc, next -> acc * next }
	}

	fun countRoughWater(patch: Patch): Int {
		val megaTile = getMegaTile(buildMap());
		val allRough = megaTile.countRough()
		for (t in megaTile.transformations()) {
			t(megaTile)
			repeat(4) {
				val count = patch.countSeaMonsterAreas(megaTile)
				if (count > 0) {
					return allRough - count
				}
				megaTile.rotateLeft()
			}
		}
		throw IllegalArgumentException("Can not find dragons")
	}
	
	private fun buildMap(): Map<Pair<Int, Int>, Tile> {
		val topLeft = corners().first()
		getConnectionsFixed(topLeft)
		val map = mutableMapOf<Pair<Int, Int>, Tile>()
		val placed = mutableSetOf<Tile>()
		val queue = mutableListOf<Pair<Pair<Int, Int>, Tile>>()
		queue.add(Pair(0, 0) to topLeft)
		while (!queue.isEmpty()) {
			val (nextCoord, nextTile) = queue.first()
			queue -= queue.first()
			if (!placed.contains(nextTile)) {
				map[nextCoord] = nextTile
				queue += getConnectionsFixed(nextTile).neighboursFromCenter(nextCoord.first, nextCoord.second)
				placed += nextTile
			}
		}
		return map;
	}

	private fun getMegaTile(map: Map<Pair<Int, Int>, Tile>): Tile {
		val minX = map.map { it.key.first }.minOrNull()!!
		val maxX = map.map { it.key.first }.maxOrNull()!!
		val minY = map.map { it.key.second }.minOrNull()!!
		val maxY = map.map { it.key.second }.maxOrNull()!!
		var collector = "tile -1:\n"
		for (y in minY..maxY) {
			collector += Tile.toStringSideBySideWithoutEdges((minX..maxX).map { x -> map[Pair(x, y)]!! })
		}
		return Tile(collector.substring(0, collector.length - 1).lines())
	}

	private fun getConnectableTile(tile: Tile) = tiles.filter { tile.getId() != it.getId() && it.canBeConnected(tile) }
	private fun getConnectionsFixed(tile: Tile) = TileConnection(tile, getConnectableTile(tile))


	private fun corners() = tiles.map { t ->
		t to (tileEdges.getValue(t) - tileEdges.filter { !(it.key.equals(t)) }.map { it.value }.flatten().toSet()).size
	}.filter { it.second == 4 }.map { it.first }

	private fun edges() = tiles.map { t ->
		t to (tileEdges.getValue(t) - tileEdges.filter { !(it.key.equals(t)) }.map { it.value }.flatten().toSet()).size
	}.filter { it.second > 0 }.map { it.first }

	private fun findACorner() = tiles.map { t ->
		t to (tileEdges.getValue(t) - tileEdges.filter { !(it.key.equals(t)) }.map { it.value }.flatten().toSet()).size
	}.find { it.second == 4 }!!


}