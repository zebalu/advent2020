package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert
import org.junit.Ignore

class ExtremeCupGameTest {
	@Test
	@Ignore("Too slow")
	fun input1() {
	    Assert.assertEquals(836763710, ExtremeCupGame("389547612").shuffle(10_000_000))
	}
	
}