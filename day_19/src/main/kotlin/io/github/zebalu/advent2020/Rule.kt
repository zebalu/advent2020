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

	fun matchesWhole(string: String, ruleProvider: (Int) -> Rule): Boolean {
		val matchResult = matches(string, ruleProvider)
		return !matchResult.isEmpty() && matchResult[0].first && matchResult[0].second.isEmpty()
	}

	private fun matches(string: String, ruleProvider: (Int) -> Rule): List<Pair<Boolean, String>> {
		if (string.isEmpty()) {
			return listOf(Pair(false, string))
		} else if (simpleRule && string[0] == char) {
			return listOf(Pair(true, string.substring(1)))
		} else {
			return rules.map { matches(string, it, ruleProvider) }.flatten().filter { it.first }
		}
	}

	private fun matches(string: String, ruleList: List<Int>, ruleProvider: (Int) -> Rule): List<Pair<Boolean, String>> {
		if (ruleList.isEmpty()) {
			return listOf(Pair(true, string))
		}
		return ruleProvider(ruleList[0]).matches(string, ruleProvider).filter { it.first }
			.map { matches(it.second, ruleList.drop(1), ruleProvider) }.flatten().filter { it.first }
	}

}