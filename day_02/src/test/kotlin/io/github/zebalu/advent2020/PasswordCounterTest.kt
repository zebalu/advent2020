package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class PasswordCountTest {
	@Test fun countValidPasswords() {
		Assert.assertEquals(410, PasswordCounter.countValidByBasicRules(ResourceReader.lines("/input2.txt")));
	}
	
	@Test fun countValidTobboganPasswords() {
		Assert.assertEquals(694, PasswordCounter.countValidByTobboganRules(ResourceReader.lines("/input2.txt")));
	}
}