package io.github.zebalu.advent2020

class JoltOrder {
	private val adapters: List<Int>;

	constructor(adapters: List<Int>) {
		adapters.asSequence().sorted().toList()
		this.adapters = adapters.asSequence().sorted().toList()
	}

	fun countDiffs(): Int {
		var sum = 0
		var diff1 = 0
		var diff3 = 0
		var idx = 0
		while (idx < adapters.size) {
			val diff = adapters[idx] - sum
			if (diff == 1) {
				++diff1
			} else if (diff == 3) {
				++diff3
			}
			sum = adapters[idx]
			++idx
		}
		++diff3
		return diff1 * diff3
	}

	fun countWays(): Long {
		val memory = mutableMapOf<Int, Long>()
		if (adapters.contains(1)) {
			memory[1] = 1
		}
		if (adapters.contains(2)) {
			memory[2] = 1
		}
		if (adapters.contains(3)) {
			memory[3] = 1
		}
		for (a in adapters) {
			val sum = (memory[a - 3] ?: 0) + (memory[a - 2] ?: 0) + (memory[a - 1] ?: 0) + (memory[a] ?: 0)
			memory[a] = sum
		}
		return memory[adapters.last()]!!
	}
}