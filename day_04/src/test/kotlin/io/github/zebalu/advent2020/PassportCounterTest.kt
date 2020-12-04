package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class PassportCounterTest {
	@Test
	fun countFake() {
		val fakeValid = PassportCounter.countFakeValid(ResourceReader.lines("/input_4.txt"))
	    Assert.assertEquals(247, fakeValid)
	}

	@Test
	fun countExtendedFake() {
		val fakeValid = PassportCounter.countExtendedFakeValid(ResourceReader.lines("/input_4.txt"))
	    Assert.assertEquals(145, fakeValid)
	}
}