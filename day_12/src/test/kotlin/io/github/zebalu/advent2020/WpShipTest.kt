package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class WpShipTest {

	@Test
	fun input1() {
		Assert.assertEquals(52742, WpShip().move(ResourceReader.lines("/input_12.txt")))
	}
	
	@Test
	fun example() {
		Assert.assertEquals(286, WpShip().move(ResourceReader.lines("/example_12.txt")))
	}

}