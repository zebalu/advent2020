package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class CupGameTest {
	@Test
	fun input1() {
		val cupGame = CupGame("389547612")
		val after100Moves = cupGame.shuffle(100)
		Assert.assertEquals("45286397", after100Moves)
	}
	
	@Test
	fun example() {
		val cupGame = CupGame("389125467")
		val after10Moves = cupGame.shuffle(10)
		Assert.assertEquals("92658374", after10Moves)
	}

}