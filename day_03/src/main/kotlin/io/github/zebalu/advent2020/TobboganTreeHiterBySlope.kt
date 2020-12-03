package io.github.zebalu.advent2020

object TobboganTreeHiterBySlope {
	fun countByBaseSlope(input: List<String>):Int {
		val map = TobboganMap(input)
		return map.countTrees(Pair(0,0), Pair(3,1))
	}
	
	fun countByAllSlope(input: List<String>):Int {
		val map = TobboganMap(input)
		val start = Pair(0,0)
		return map.countTrees(start, Pair(1,1)) *
				map.countTrees(start, Pair(3,1)) *
				map.countTrees(start, Pair(5,1)) *
				map.countTrees(start, Pair(7,1)) *
				map.countTrees(start, Pair(1,2))
	}
}