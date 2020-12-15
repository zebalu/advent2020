package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class SmartMemoryGameTest {

	@Test
	fun example1() {
		Assert.assertEquals(175594, SmartMemoryGame("0,3,6".split(",").map { it.toInt() }).findNth(30_000_000))
	}

	@Test
	fun input1() {
		Assert.assertEquals(9136, SmartMemoryGame("11,0,1,10,5,19".split(",").map { it.toInt() }).findNth(30_000_000))
	}
}