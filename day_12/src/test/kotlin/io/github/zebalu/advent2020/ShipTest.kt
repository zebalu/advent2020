package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class ShipTest {

	@Test
    fun input1() {
		val ship = Ship()
		Assert.assertEquals(1010, ship.move(ResourceReader.lines("/input_12.txt")))
	}

}