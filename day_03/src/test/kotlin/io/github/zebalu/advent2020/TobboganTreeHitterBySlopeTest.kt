package io.github.zebalu.advent2020
import org.junit.Test
import io.github.zebalu.advent2020.TobboganMap
import org.junit.Assert

class TobboganTreeHitterBySlopeTest {
	@Test fun baseSlope() {
		Assert.assertEquals(282, TobboganTreeHiterBySlope.countByBaseSlope(ResourceReader.lines("/input_3.txt")))
	}
	
	@Test fun allSlopes() {
		Assert.assertEquals(958815792, TobboganTreeHiterBySlope.countByAllSlope(ResourceReader.lines("/input_3.txt")))
	}
}