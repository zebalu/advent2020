package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class IdealTimeFinderTest {
	@Test
	fun example() {
		Assert.assertEquals(
			1068781,
			IdealTimeFinder.findTime(BusReader.readBusRequest(ResourceReader.lines("/example_13.txt")))
		)
	}

	@Test
	fun input1() {
		Assert.assertEquals(
			775230782877242,
			IdealTimeFinder.findTime(BusReader.readBusRequest(ResourceReader.lines("/input_13.txt")))
		)
	}

	@Test
	fun example2() {
		Assert.assertEquals(
			3417,
			IdealTimeFinder.findTime(BusReader.readBusRequest("\n17,x,13,19".lines()))
		)
	}
	
	@Test
	fun example3() {
		Assert.assertEquals(
			754018,
			IdealTimeFinder.findTime(BusReader.readBusRequest("\n67,7,59,61".lines()))
		)
	}
	
	@Test
	fun example4() {
		Assert.assertEquals(
			779210,
			IdealTimeFinder.findTime(BusReader.readBusRequest("\n67,x,7,59,61".lines()))
		)
	}
	
	@Test
	fun example5() {
		Assert.assertEquals(
			1261476,
			IdealTimeFinder.findTime(BusReader.readBusRequest("\n67,7,x,59,61".lines()))
		)
	}
	
	@Test
	fun example6() {
		Assert.assertEquals(
			1202161486,
			IdealTimeFinder.findTime(BusReader.readBusRequest("\n1789,37,47,1889".lines()))
		)
	}
	
}