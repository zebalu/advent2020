package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class RecursiveCardGameTest {
	
	@Test
	fun input1() {
		val rcg = RecursiveCardGame(ResourceReader.lineGroups("/input_22.txt"))
		Assert.assertEquals(35055, rcg.calculateScore())
	}
}