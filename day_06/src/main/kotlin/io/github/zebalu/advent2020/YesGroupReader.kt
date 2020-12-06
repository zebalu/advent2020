package io.github.zebalu.advent2020

import java.util.ArrayList

object YesGroupReader {
	
	fun countYeses(groups: List<List<String>>): Int {
		return groups.map { g -> countGroupYeses(g) }.sum()
	}

	fun countCommonYeses(groups: List<List<String>>): Int {
		return groups.map { g -> countCommonGroupYeses(g) }.sum()
	}

	private fun countGroupYeses(group: List<String>): Int {
		return group.map { m -> m.toCharArray().toSet() }.fold(
			HashSet<Char>(),
			{ acc, elem ->
				acc.addAll(elem)
				acc
			}
		).size
	}

	private fun countCommonGroupYeses(group: List<String>): Int {
		fun f(group: List<String>, i: Int, common: Set<Char>): Int {
			return if (common.size == 0) 0
			else if (i == group.size) common.size
			else f(group, i + 1, common.intersect(group[i].toCharArray().toSet()))
		}
		return f(group, 1, group[0].toCharArray().toSet())
	}
}