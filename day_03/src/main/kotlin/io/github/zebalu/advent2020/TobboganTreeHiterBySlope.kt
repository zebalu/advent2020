package io.github.zebalu.advent2020

object TobboganTreeHiterBySlope {
	fun countByBaseSlope(input: List<String>): Int {
		val map = TobboganMap(input)
		return map.countTrees(Pair(0, 0), Pair(3, 1))
	}

	fun countByAllSlope(input: List<String>): Int {
		val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
		val map = TobboganMap(input)
		val start = Pair(0, 0)
		return slopes.map { slope -> map.countTrees(start, slope) }.fold(1, { acc, prod -> acc * prod })
	}
}