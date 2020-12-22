package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class CardgameTest {

	@Test
	fun input1() {
		val cg = CardGame(ResourceReader.lineGroups("/input_22.txt"))
		Assert.assertEquals(33098, cg.calculateScore())
	}
}