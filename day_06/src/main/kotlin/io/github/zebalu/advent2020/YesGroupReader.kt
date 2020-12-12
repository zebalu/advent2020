package io.github.zebalu.advent2020

import java.util.ArrayList

object YesGroupReader {

	fun countYeses(groups: List<List<String>>) = groups.map { g -> countGroupYeses(g) }.sum()

	fun countCommonYeses(groups: List<List<String>>) = groups.map { g -> countCommonGroupYeses(g) }.sum()

	private fun countGroupYeses(group: List<String>) =
		group.map { m -> m.toCharArray().toSet() }.reduce({ accumulator, next -> accumulator + next }).size

	private fun countCommonGroupYeses(group: List<String>) =
		group.map { m -> m.toCharArray().toSet() }.reduce { a, b -> a.intersect(b) }.size

}