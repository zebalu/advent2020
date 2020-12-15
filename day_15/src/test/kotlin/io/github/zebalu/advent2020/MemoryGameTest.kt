package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class MemoryGameTest {

	@Test
	fun example1() {
		Assert.assertEquals(436, MemoryGame("0,3,6".split(",").map { it.toInt() }).findNth(2020))
	}

	@Test
	fun input1() {
		Assert.assertEquals(870, MemoryGame("11,0,1,10,5,19".split(",").map { it.toInt() }).findNth(2020))
	}
}