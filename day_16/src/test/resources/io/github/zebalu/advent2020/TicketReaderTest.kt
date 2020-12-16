package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class TicketReaderTest {
	@Test
	fun input1() {
		Assert.assertEquals(22073, TicketReader.scanningRate(ResourceReader.lineGroups("/input_16.txt")))
	}
	
	@Test
	fun input2() {
		Assert.assertEquals(1346570764607, TicketReader.departure(ResourceReader.lineGroups("/input_16.txt")))
	}

}