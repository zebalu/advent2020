package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class Cube3DTest {

	@Test
	fun input1() {
		val cube = Cube3D(ResourceReader.lines("/input_17.txt"))
		for (i in 1..6) {
			cube.iterate()
		}
		Assert.assertEquals(265, cube.countActive())
	}
}