package io.github.zebalu.advent2020

import java.lang.Exception

class Rule(private val id: Int, ruleDescription: String) {

	private companion object {
		public val simpleChar = Regex("\"\\w\"")
		public fun toRules(rules: String) =
			rules.split("|").map { it.trim() }.map { it.split(" ") }.map { l -> l.map { it.trim().toInt() } }
	}

	val simpleRule = ruleDescription.matches(simpleChar)
	val char = if (simpleRule) ruleDescription[1] else null
	val rules = if (!simpleRule) toRules(ruleDescription) else emptyList()

	fun matchesWhole(string: String, ruleProvider: (Int) -> Rule) =
		matches(string, ruleProvider).any { it.first && it.second.isEmpty() }

	private fun matches(string: String, ruleProvider: (Int) -> Rule) =
		if (string.isEmpty())
			listOf(Pair(false, string))
		else if (simpleRule && string[0] == char)
			listOf(Pair(true, string.substring(1)))
		else
			rules.map { matchesRuleList(string, it, ruleProvider) }.flatten().filter { it.first }

	private fun matchesRuleList(
		string: String,
		ruleList: List<Int>,
		ruleProvider: (Int) -> Rule
	): List<Pair<Boolean, String>> =
		if (ruleList.isEmpty())
			listOf(Pair(true, string))
		else
			ruleProvider(ruleList[0]).matches(string, ruleProvider).filter { it.first }
				.map { matchesRuleList(it.second, ruleList.drop(1), ruleProvider) }.flatten().filter { it.first }

}