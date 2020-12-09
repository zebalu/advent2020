package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class XmasBreakertest {

	@Test fun example1() {
		val lines = ResourceReader.lines("/example_9.txt")
		val xmassBreaker = XmasBreaker()
		Assert.assertEquals(127, xmassBreaker.findFirstBadNum(lines, 5))
	}
	
	@Test fun input1() {
		val lines = ResourceReader.lines("/input_9.txt")
		val xmassBreaker = XmasBreaker()
		Assert.assertEquals(144381670, xmassBreaker.findFirstBadNum(lines, 25))
	}

	@Test fun example2() {
		val lines = ResourceReader.lines("/example_9.txt")
		val xmassBreaker = XmasBreaker()
		Assert.assertEquals(62, xmassBreaker.findDecode(lines, 5))
	}
	
	@Test fun input2() {
		val lines = ResourceReader.lines("/input_9.txt")
		val xmassBreaker = XmasBreaker()
		Assert.assertEquals(20532569, xmassBreaker.findDecode(lines, 25))
	}
	
}