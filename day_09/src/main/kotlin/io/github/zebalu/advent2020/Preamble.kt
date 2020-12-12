package io.github.zebalu.advent2020

class Preamble(private val size: Int) {
	var reached: Boolean = false

	val list: MutableList<Int> = mutableListOf<Int>()

	fun add(num: Int): Boolean {
		if (!reached) {
			list.add(num)
			reached = list.size >= size
			return true
		} else {
			val set = list.subList(list.size - size, list.size).toSet()
			val canBeAdded = set.any { i -> num - i != i && set.contains(num - i) }
			if (canBeAdded) {
				list.add(num)
			}
			return canBeAdded
		}
	}

	fun findDecodeFor(num: Int): Int {
		var start = 0
		while (start < list.size - 2) {
			var range = 2
			while (range <= list.size - start) {
				val found = list.subList(start, start + range).sum() == num
				if (found) {
					val theRange = list.subList(start, start + range)
					theRange.sort()
					return theRange[0] + theRange[theRange.size - 1]
				}
				++range
			}
			++start
		}
		throw IllegalArgumentException("Can not work with num: " + num)
	}
}