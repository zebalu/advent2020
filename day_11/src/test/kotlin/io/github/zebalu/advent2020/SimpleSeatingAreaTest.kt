package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class SimpleSeatingAreaTest {

	@Test
	fun example1() {
	    Assert.assertEquals(37, SimpleSeatingArea(ResourceReader.lines("/example_11.txt")).countStableState())
	}
	
	@Test
	fun input1() {
	    Assert.assertEquals(2386, SimpleSeatingArea(ResourceReader.lines("/input_11.txt")).countStableState())
	}

}