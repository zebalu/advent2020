package io.github.zebalu.advent2020

class TileConnection(val central: Tile, neighbours: List<Tile>) {
	private val north: Tile? = neighbours.find { findNorth(it) != null }
	private val south: Tile? = neighbours.find { findSouth(it) != null }
	private val west: Tile? = neighbours.find { findWest(it) != null }
	private val east: Tile? = neighbours.find { findEast(it) != null }

	fun neighbourCount(): Int {
		var count = 0
		if (hasNorth()) ++count
		if (hasSouth()) ++count
		if (hasEast()) ++count
		if (hasWest()) ++count
		return count
	}

	fun hasNorth() = north != null
	fun hasSouth() = south != null
	fun hasEast() = east != null
	fun hasWest() = west != null

	fun getNorth() = if (hasNorth()) north!! else throw IllegalStateException("Has no norh")
	fun getSouth() = if (hasSouth()) south!! else throw IllegalStateException("Has no south")
	fun getEast() = if (hasEast()) east!! else throw IllegalStateException("Has no east")
	fun getWest() = if (hasWest()) west!! else throw IllegalStateException("Has no west")

	fun neighboursFromCenter(x: Int, y: Int): List<Pair<Pair<Int, Int>, Tile>> {
		val result = mutableListOf<Pair<Pair<Int, Int>, Tile>>()
		north?.let { result.add(Pair(Pair(x, y - 1), it)) }
		south?.let { result.add(Pair(Pair(x, y + 1), it)) }
		east?.let { result.add(Pair(Pair(x + 1, y), it)) }
		west?.let { result.add(Pair(Pair(x - 1, y), it)) }
		return result
	}

	override fun toString() = "${central.getId()}:\t" +
			(if (hasNorth()) "N: ${north!!.getId()}, " else "N: null, ") +
			(if (hasSouth()) "S: ${south!!.getId()}, " else "S: null, ") +
			(if (hasEast()) "E: ${east!!.getId()}, " else "E: null, ") +
			(if (hasWest()) "W: ${west!!.getId()}, " else "W: null, ")

	private fun findNorth(tile: Tile) = transformToMatch(central.getTopEdge(), tile, Tile::getBottomEdge)
	private fun findSouth(tile: Tile) = transformToMatch(central.getBottomEdge(), tile, Tile::getTopEdge)
	private fun findEast(tile: Tile) = transformToMatch(central.getRightEdge(), tile, Tile::getLeftEdge)
	private fun findWest(tile: Tile) = transformToMatch(central.getLeftEdge(), tile, Tile::getRightEdge)

	private fun transformToMatch(edge: List<Char>, tile: Tile, getEdge: (Tile) -> List<Char>): Tile? {
		tile.transformations().forEach { transformation ->
			transformation(tile)
			repeat(4) {
				if (edge.equals(getEdge(tile))) {
					return tile
				}
				tile.rotateLeft()
			}
		}
		return null
	}
}