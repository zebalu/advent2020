package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class BusFinderTest {
	@Test
	fun example() {
		Assert.assertEquals(295, BusReader.readLines(ResourceReader.lines("/example_13.txt")).nextIdTimeminutes())
	}
	
	@Test
	fun input1() {
		Assert.assertEquals(8063, BusReader.readLines(ResourceReader.lines("/input_13.txt")).nextIdTimeminutes())
	}

}