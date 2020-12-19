package io.github.zebalu.advent2020

object RuleReader {

	fun ruleMap(lineGroups: List<List<String>>): Pair<Map<Int, Rule>, List<String>> {
		val map = lineGroups[0].map { line ->
			val parts = line.split(": ")
			val id = parts[0].toInt()
			parts[0].toInt() to Rule(id, parts[1])
		}.toMap()
		return Pair(map, lineGroups[1])
	}

	fun countMatchesTo(id: Int, lines: List<String>, rules: Map<Int, Rule>): Int {
		val rule = rules[id]!!
		return lines.count { rule.matchesWhole(it, rules::getValue) }
	}
	
	fun updatedRuleMap(lineGroups: List<List<String>>): Pair<Map<Int, Rule>, List<String>> {
		val map = lineGroups[0].map { line ->
			val parts = line.split(": ")
			val id = parts[0].toInt()
			parts[0].toInt() to Rule(id, parts[1])
		}.toMap().toMutableMap()
		map.put(8, Rule(8, "42 | 42 8"))
		map.put(11, Rule(11, "42 31 | 42 11 31"))
		return Pair(map, lineGroups[1])
	}

}