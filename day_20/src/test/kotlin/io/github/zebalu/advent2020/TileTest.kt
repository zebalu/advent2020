package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class TileTest {

	@Test
	fun toStringTest() {
		val tile = Tile(tileLines)
		Assert.assertEquals(tilePic, tile.toString())
	}

	@Test
	fun rotateLeft() {
		val tile = Tile(tileLines)
		tile.rotateLeft()
		Assert.assertEquals(
			"""..#
.##
###""",
			tile.toString()
		)
	}

	@Test
	fun rotateRight() {
		val tile = Tile(tileLines)
		tile.rotateRight()
		Assert.assertEquals(
			"""###
##.
#..""",
			tile.toString()
		)
	}
	
	@Test
	fun flip() {
		val tile = Tile(tileLines)
		tile.horizontalFlip()
		Assert.assertEquals(
			"""###
##.
#..""",
			tile.toString()
		)
	}

	@Test
	fun idIsGood() {
		val tile = Tile(tileLines)
		Assert.assertEquals(12, tile.getId())
	}
	
	@Test
	fun cloneCopiesId() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		Assert.assertEquals(tile, clone)
	}
	
	@Test
	fun safeToManipulateclone() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.rotateLeft()
		Assert.assertEquals(tile.getTopEdge(), clone.getLeftEdge().reversed())
	}
	
	@Test
	fun canBeAbove() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		Assert.assertTrue(tile.canBeAbove(clone))
	}
	
	@Test
	fun canBeAboveFlipped() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.horizontalFlip()
		Assert.assertTrue(tile.canBeAbove(clone))
	}
	
	@Test
	fun canBeAboveRejectBadTile() {
		val tile = Tile(tileLines)
		val other = Tile(otherTileLines)
		Assert.assertFalse(tile.canBeAbove(other))
	}
	
	@Test
	fun fitsOnTop() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.horizontalFlip()
		Assert.assertTrue(tile.fitsOnTop(clone))
	}
	
	@Test
	fun fitsOnBottom() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.horizontalFlip()
		Assert.assertTrue(clone.fitsOnBottom(tile))
	}
	
	@Test
	fun fitsOnLeft() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.verticalFlip()
		clone.horizontalFlip()
		clone.rotateRight()
		Assert.assertTrue(tile.fitsOnLeft(clone))
	}
	
	@Test
	fun fitsOnRight() {
		val tile = Tile(tileLines)
		val clone = tile.clone()
		clone.verticalFlip()
		clone.horizontalFlip()
		clone.rotateRight()
		Assert.assertTrue(clone.fitsOnRight(tile))
	}
	

	private val tileTile = "Tile 12:"
	private val tilePic = """#..
##.
###"""
	private val tileLines = listOf(tileTile) + tilePic.lines()
	
	private val otherTileLines = listOf("Title 11:", "...", ".#.", "...")
}