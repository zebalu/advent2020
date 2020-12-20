package io.github.zebalu.advent2020

class Tile(lines: List<String>) : Cloneable {

	private val id = lines[0].split(" ")[1].let { it.substring(0, it.length - 1).toLong() }
	private var area =
		lines.drop(1).mapIndexed { y, line -> line.mapIndexed { x, c -> Pair(x, y) to c } }.flatten().toMap()
	private val allPosibleEdges = collectAllPossibleEdges()

	fun maxX(): Int = area.keys.map { it.first }.maxOrNull()!!
	fun maxY(): Int = area.keys.map { it.second }.maxOrNull()!!

	fun getLine(lineNum: Int) = (0..maxX()).map { x -> area.getValue(Pair(x, lineNum)) }
	fun getColumn(colNum: Int) = (0..maxY()).map { y -> area.getValue(Pair(colNum, y)) }
	fun getTopEdge() = getLine(0)
	fun getBottomEdge() = getLine(maxY())
	fun getLeftEdge() = getColumn(0)
	fun getRightEdge() = getColumn(maxX())
	fun getId() = id

	fun rotateLeft() {
		val maxX = maxX()
		val maxY = maxY()
		area = (0..maxX).map { x ->
			(0..maxY).map { y ->
				Pair(y, maxX - x) to area.getValue(Pair(x, y))
			}
		}.flatten().toMap()
	}

	fun rotateRight() {
		val maxX = maxX()
		val maxY = maxY()
		area = (0..maxX).map { x ->
			(0..maxY).map { y ->
				Pair(maxY - y, x) to area.getValue(Pair(x, y))
			}
		}.flatten().toMap()
	}

	fun rotate180() {
		rotateLeft()
		rotateLeft()
	}

	fun horizontalFlip() {
		val maxX = maxX()
		val maxY = maxY()
		area = (0..maxX).map { x ->
			(0..maxY).map { y ->
				Pair(x, maxY - y) to area.getValue(Pair(x, y))
			}
		}.flatten().toMap()
	}

	fun verticalFlip() {
		val maxX = maxX()
		val maxY = maxY()
		area = (0..maxX).map { x ->
			(0..maxY).map { y ->
				Pair(maxX - x, y) to area.getValue(Pair(x, y))
			}
		}.flatten().toMap()
	}

	private fun identical() {

	}

	fun canBeConnected(other: Tile) = !((allPosibleEdges.intersect(other.allPosibleEdges)).isEmpty())

	fun fitsOnTop(other: Tile) = getBottomEdge().equals(other.getTopEdge())

	fun fitsOnBottom(other: Tile) = other.fitsOnTop(this)

	fun fitsOnLeft(other: Tile) = getRightEdge().equals(other.getLeftEdge())

	fun fitsOnRight(other: Tile) = other.fitsOnLeft(this)

	fun canBeAbove(other: Tile) = allPosibleEdges.contains(other.getTopEdge())

	fun canBeBellow(other: Tile) = other.canBeAbove(this)

	fun canBeLeftFrom(other: Tile) = allPosibleEdges.contains(other.getLeftEdge())

	fun canBeRightFrom(other: Tile) = other.canBeLeftFrom(this)

	fun getAllPossibleEdges() = allPosibleEdges

	fun collectAllPossibleEdges() =
		listOf(getLeftEdge(), getRightEdge(), getTopEdge(), getBottomEdge()).map { listOf(it, it.reversed()) }.flatten()
			.toSet()

	fun allPatches(width: Int, height: Int) =
		(0..maxX() - width).map { x ->
			(0..maxY() - height).map { y ->
				(0..width).map { dx ->
					(0..height).map { dy ->
						Pair(dx, dy) to area[Pair(x + dx, y + dy)]!!
					}
				}.flatten().toMap()
			}
		}.flatten()

	fun countRough() = area.keys.filter { area[it] == '#' }.count()

	private fun matchAllWays(
		other: Tile,
		thisEdge: List<Char>,
		otherEdge: (Tile) -> List<Char>,
		transformations: List<(Tile) -> Unit>
	): Boolean {
		val clone = other.clone()
		transformations.forEach { t ->
			t(clone)
			if (matchByRotate(clone, thisEdge, otherEdge)) {
				return true
			}
		}
		return false
	}

	private fun matchByRotate(other: Tile, thisEdge: List<Char>, otherEdge: (Tile) -> List<Char>): Boolean {
		repeat(4) {
			if (thisEdge.equals(otherEdge(other))) {
				return true
			}
			other.rotateLeft()
		}
		return false
	}

	fun transformations() =
		listOf(Tile::identical, Tile::horizontalFlip, Tile::verticalFlip, Tile::horizontalFlip, Tile::verticalFlip)

	fun tran2() = listOf(
		Tile::identical, Tile::horizontalFlip, Tile::verticalFlip, Tile::rotateLeft, Tile::rotateRight,
		Tile::rotate180, { t: Tile -> t.rotateLeft(); t.verticalFlip() },
		{ t: Tile -> t.rotateRight(); t.verticalFlip() },
		{ t: Tile -> t.rotate180(); t.verticalFlip() },
		{ t: Tile -> t.rotateLeft(); t.horizontalFlip() },
		{ t: Tile -> t.rotateRight(); t.horizontalFlip() },
		{ t: Tile -> t.rotate180(); t.horizontalFlip() }
	)


	override fun hashCode(): Int = id.toInt() * 119
	override fun equals(other: Any?): Boolean = if (other is Tile) other.id == id else false
	override fun toString(): String {
		var result = ""
		for (y in 0..maxY()) {
			for (x in 0..maxX()) {
				result += area.getValue(Pair(x, y))
			}
			result += '\n'
		}
		return result.substring(0, result.length - 1)
	}

	override public fun clone(): Tile = super.clone() as Tile

	companion object {
		fun toStringSideBySide(tiles: List<Tile>, separator: String = "\t"): String {
			var result = ""
			for (y in 0..tiles[0].maxY()) {
				for (tile in tiles) {
					for (x in 0..tiles[0].maxX()) {
						result += tile.area.getValue(Pair(x, y))
					}
					result += separator
				}
				result += '\n'
			}
			return result
		}

		fun toStringSideBySideWithoutEdges(tiles: List<Tile>): String {
			var result = ""
			for (y in 1..tiles[0].maxY() - 1) {
				for (tile in tiles) {
					for (x in 1..tiles[0].maxX() - 1) {
						result += tile.area.getValue(Pair(x, y))
					}
				}
				result += '\n'
			}
			return result
		}

	}

}