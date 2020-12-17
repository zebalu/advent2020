package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert
import java.time.Instant
import java.time.Duration

class Cube4DTest {
	
	@Test
	fun input1() {
		val cube = Cube4D(ResourceReader.lines("/input_17.txt"))
		for (i in 1..6) {
			cube.iterate()
		}
		Assert.assertEquals(1936, cube.countActive())
	}
}