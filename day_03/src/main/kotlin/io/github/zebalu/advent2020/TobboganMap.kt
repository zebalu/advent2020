package io.github.zebalu.advent2020

import java.util.HashMap

class TobboganMap {
	private val map: MutableMap<Pair<Int, Int>, String> = HashMap();
	private val width: Int;
	private val height: Int;

	constructor(lines: List<String>) {
		width = lines[0].length;
		height = lines.size;
		var y = 0
		while (y < height) {
			var x = 0
			while (x < width) {
				map.put(Pair(x, y), "" + lines[y][x])
				++x
			}
			++y
		}
	}

	fun countTrees(from: Pair<Int, Int>, slope: Pair<Int, Int>): Int {
		var pos = from
		var count = 0
		if (map.get(pos).equals("#")) {
			++count
		}
		while (pos.second < height) {
			pos = Pair((pos.first + slope.first) % width, pos.second + slope.second)
			if (map.get(pos).equals("#")) {
				++count
			}
		}
		return count
	}
}