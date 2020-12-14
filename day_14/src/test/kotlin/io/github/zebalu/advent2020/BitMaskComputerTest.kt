package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class BitMaskComputerTest {
	@Test
	fun example() {
		Assert.assertEquals(165, BMComputerReader.executeComputer(ResourceReader.lines("/example_14.txt")))
	}
	
	@Test
	fun input1() {
		Assert.assertEquals(11327140210986, BMComputerReader.executeComputer(ResourceReader.lines("/input_14.txt")))
	}
	
	@Test
	fun example2() {
		Assert.assertEquals(208, BMComputerReader.executeComputer2(ResourceReader.lines("/example_14_2.txt")))
	}
	
	@Test
	fun input2() {
		Assert.assertEquals(2308180581795, BMComputerReader.executeComputer2(ResourceReader.lines("/input_14.txt")))
	}

}