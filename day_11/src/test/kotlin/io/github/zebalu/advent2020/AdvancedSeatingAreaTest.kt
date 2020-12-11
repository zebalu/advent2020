package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class AdvancedSeatingAreaTest {
	@Test
	fun example1() {
		Assert.assertEquals(26, AdvancedSeatingArea(ResourceReader.lines("/example_11.txt")).countStableState())
	}

	@Test
	fun input1() {
		Assert.assertEquals(2091, AdvancedSeatingArea(ResourceReader.lines("/input_11.txt")).countStableState())
	}
}