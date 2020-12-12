package io.github.zebalu.advent2020

class TobboganMap {
	private val map: Map<Pair<Int, Int>, Char>;
	private val width: Int;
	private val height: Int;

	constructor(lines: List<String>) {
		width = lines[0].length;
		height = lines.size;
		var y = 0
		val collectorMap = mutableMapOf<Pair<Int, Int>, Char>();
		while (y < height) {
			var x = 0
			while (x < width) {
				collectorMap.put(Pair(x, y), lines[y][x])
				++x
			}
			++y
		}
		map = collectorMap
	}

	fun countTrees(from: Pair<Int, Int>, slope: Pair<Int, Int>): Int {
		var pos = from
		var count = 0
		if (map.get(pos) == '#') {
			++count
		}
		while (pos.second < height) {
			pos = Pair((pos.first + slope.first) % width, pos.second + slope.second)
			if (map.get(pos) == '#') {
				++count
			}
		}
		return count
	}
}