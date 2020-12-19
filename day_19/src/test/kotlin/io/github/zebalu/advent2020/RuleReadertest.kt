package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class RuleReadertest {
	@Test
	fun input1() {
		val (rules, lines) = RuleReader.ruleMap(ResourceReader.lineGroups("/input_19.txt"))
		Assert.assertEquals(205, RuleReader.countMatchesTo(0, lines, rules))
	}

	@Test
	fun example1() {
		val (rules, lines) = RuleReader.ruleMap(
			listOf(
				"""0: 1 2
1: "a"
2: 1 3 | 3 1
3: "b"""".lines(),
				listOf("aab")
			)
		)
		Assert.assertEquals(1, RuleReader.countMatchesTo(0, lines, rules))
	}

	@Test
	fun example1_2() {
		val (rules, lines) = RuleReader.ruleMap(
			listOf(
				"""0: 1 2
1: "a"
2: 1 3 | 3 1
3: "b"""".lines(),
				listOf("aba")
			)
		)
		Assert.assertEquals(1, RuleReader.countMatchesTo(0, lines, rules))
	}

	@Test
	fun example1_3() {
		val (rules, lines) = RuleReader.ruleMap(
			listOf(
				"""0: 1 2
1: "a"
2: 1 3 | 3 1
3: "b"""".lines(),
				listOf("abb")
			)
		)
		Assert.assertEquals(0, RuleReader.countMatchesTo(0, lines, rules))
	}

	@Test
	fun example1_4() {
		val (rules, lines) = RuleReader.ruleMap(
			listOf(
				"""0: 1 2
1: "a"
2: 1 3 | 3 1
3: "b"""".lines(),
				listOf("aabb")
			)
		)
		Assert.assertEquals(0, RuleReader.countMatchesTo(0, lines, rules))
	}

	@Test
	fun input2() {
		val (rules, lines) = RuleReader.updatedRuleMap(ResourceReader.lineGroups("/input_19.txt"))
		Assert.assertEquals(329, RuleReader.countMatchesTo(0, lines, rules))
	}

}