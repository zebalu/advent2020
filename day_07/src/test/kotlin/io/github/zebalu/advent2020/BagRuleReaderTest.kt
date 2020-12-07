package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class BagRuleReaderTest {
	@Test
	fun countWaysExample() {
		val rules = BagRuleReader.readRules(ResourceReader.lines("/example_7.txt"))
		Assert.assertEquals(4, BagRuleReader.countWaysToShinyGold(rules))
	}

	@Test
	fun countWays() {
		val rules = BagRuleReader.readRules(ResourceReader.lines("/input_7.txt"))
		Assert.assertEquals(235, BagRuleReader.countWaysToShinyGold(rules))
	}

	@Test
	fun countContentExample() {
		Assert.assertEquals(
			32,
			BagRuleReader.countContentInShinyGold(BagRuleReader.readRules(ResourceReader.lines("/example_7.txt")))
		)
	}

	@Test
	fun countContent() {
		Assert.assertEquals(
			158493,
			BagRuleReader.countContentInShinyGold(BagRuleReader.readRules(ResourceReader.lines("/input_7.txt")))
		)
	}
}