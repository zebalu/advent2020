package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class TileFlipperTest {
	@Test
	fun input1() {
		val tf = TileFlipper(ResourceReader.lines("/input_24.txt"))
		Assert.assertEquals(346, tf.countTiles())
	}
	
	@Test
	fun input2() {
		val tf = TileFlipper(ResourceReader.lines("/input_24.txt"))
		tf.flip(100)
		Assert.assertEquals(3802, tf.countTiles())
	}

}