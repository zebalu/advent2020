package io.github.zebalu.advent2020

data class ValueNext(val value: Int, val next: Int)

class ExtremeCupGame(cupList: String) {
	val followUpMap = firstMillion(cupList)// toFollowupMap(cupList)
	val valueIndexMap = followUpMap.map { it.value.value to it.key }.toMap()
	val cups = getToMillion(cupList)
	val wrapNum = cups.maxOrNull()!! + 1
	var currentCup = 0

	fun shuffle(times: Int): Long {
		repeat(times) {
			val pickedUp = pickUp3Cups()
			val nextDest = chooseDestinationCup(pickedUp.first)
			insertCups(nextDest, pickedUp.first, pickedUp.second)
			nextCup()
		}
		val idx1 = valueIndexMap[1]!!
		return followUpMap[followUpMap[idx1]!!.next]!!.value.toLong() * followUpMap[followUpMap[followUpMap[idx1]!!.next]!!.next]!!.value.toLong()
	}

	private fun pickUp3Cups(): Pair<List<ValueNext>, Int> {
		val cvn = followUpMap[currentCup]!!
		val cPlus1 = followUpMap[cvn.next]!!
		val cPlus2 = followUpMap[cPlus1.next]!!
		val cPlus3 = followUpMap[cPlus2.next]!!
		followUpMap[currentCup] = ValueNext(cvn.value, cPlus3.next)
		return Pair(listOf(cPlus1, cPlus2, cPlus3), cvn.next)
	}

	private fun chooseDestinationCup(notList: List<ValueNext>): Int {
		var prev = followUpMap[currentCup]!!.value - 1
		if (prev < 1) {
			prev = followUpMap.size
		}
		val notSet = notList.map { it.value }.toSet()
		while (notSet.contains(prev)) {
			prev = prev - 1
			if (prev < 1) {
				prev = followUpMap.size
			}
		}
		return valueIndexMap[prev]!!
	}

	private fun insertCups(afterIdx: Int, cupsToInsert: List<ValueNext>, idx: Int) {
		val next = followUpMap[afterIdx]!!
		followUpMap[afterIdx] = ValueNext(next.value, idx)
		followUpMap[cupsToInsert[1].next] = ValueNext(cupsToInsert[2].value, next.next)
	}

	private fun nextCup() {
		currentCup = followUpMap[currentCup]!!.next
	}

	private fun toGameString() =
		cups.mapIndexed { i, v -> if (i == currentCup) "($v)" else "$v" }.reduce { a, n -> "$a, $n" }

	private fun getToMillion(cupString: String): MutableList<Int> {
		val result = cupString.map { "$it".toInt() }.toMutableList()
		var next = result.maxOrNull()!!
		while (next < 1_000_000) {
			++next
			result += next
		}
		return result
	}

	private fun idxOf(value: Int): Int {
		for (i in 0..(followUpMap.size - 1)) {
			if (followUpMap[i]!!.value == value) {
				return i
			}
		}
		throw IllegalStateException("can not find value: $value")
	}

	private fun take(n: Int): List<ValueNext> {
		val result = mutableListOf<ValueNext>()
		var next = followUpMap[0]!!
		while (result.size < n) {
			result += next
			next = followUpMap[next.next]!!
		}
		return result
	}

	private fun toFollowupMap(str: String): MutableMap<Int, ValueNext> {
		val result = mutableMapOf<Int, ValueNext>()
		val ints = str.map { "$it".toInt() }
		var i = 0
		while (i < ints.size) {
			result[i] = ValueNext(ints[i], (i + 1) % ints.size)
			++i
		}
		return result
	}

	private fun firstMillion(str: String): MutableMap<Int, ValueNext> {
		val result = mutableMapOf<Int, ValueNext>()
		val ints = str.map { "$it".toInt() }
		var next = ints.maxOrNull()!!
		var i = 0
		while (result.size < 1_000_000) {
			val v = if (i < ints.size) ints[i] else ++next
			val n = i + 1
			result[i] = ValueNext(v, n % 1_000_000)
			++i
		}
		return result
	}
}