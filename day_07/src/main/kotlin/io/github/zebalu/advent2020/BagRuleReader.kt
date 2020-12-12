package io.github.zebalu.advent2020

typealias  Rules = Map<String, Set<Pair<Int, String>>>

object BagRuleReader {

	fun readRules(lines: List<String>): Rules {
		val result = mutableMapOf<String, MutableSet<Pair<Int, String>>>()
		lines.forEach { line ->
			val parts = line.split(" bags contain ")
			val type = parts[0]
			parts[1].split(", ").map { s -> s.split(" bag")[0] }.forEach { bag ->
				val split = bag.split(" ")
				if (split.size != 2 && split.size != 3) {
					throw IllegalStateException("split is not 2 or 3 long: " + split)
				}
				val count = if ("no".equals(split[0])) 0 else split[0].toInt()
				val bagName = if (count == 0) "no other" else split[1] + " " + split[2]
				result.computeIfAbsent(type, { _ -> mutableSetOf<Pair<Int, String>>() }).add(Pair(count, bagName))
			}
		}
		return result
	}

	fun countWaysToShinyGold(rules: Map<String, Set<Pair<Int, String>>>) =
		rules.keys.count { name ->
			(!("shiny gold".equals(name))) && isThereWayToShinyGold(
				name,
				rules,
				setOf(name)
			)
		}


	fun countContentInShinyGold(rules: Rules) = countContent("shiny gold", rules)

	private fun countContent(name: String, rules: Rules): Int {
		return rules.get(name)?.map { pair ->
			pair.first + pair.first * countContent(pair.second, rules)
		}?.sum() ?: 0
	}

	private fun isThereWayToShinyGold(name: String, rules: Rules, visited: Set<String>): Boolean {
		if ("shiny gold".equals(name)) {
			return true
		}
		val col = rules.get(name)?.map { pair ->
			if (pair.first == 0) false
			else if ("shiny gold".equals(pair.second)) true
			else if (visited.contains(pair.second)) false
			else isThereWayToShinyGold(
				pair.second,
				rules,
				visited + pair.second
			)
		}
		return if (col == null || col.isEmpty()) false else col.reduce { acc, next -> acc || next }
	}
}