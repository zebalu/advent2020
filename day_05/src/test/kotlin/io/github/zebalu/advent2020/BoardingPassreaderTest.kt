package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class BoardingPassreaderTest {
	@Test
	fun highestId() {
		Assert.assertEquals(818, BoardingPassReader.getHighestId(ResourceReader.lines("/input_5.txt")))
	}

	@Test
	fun findMissingId() {
		Assert.assertEquals(559, BoardingPassReader.getMissingId(ResourceReader.lines("/input_5.txt")))
	}
}