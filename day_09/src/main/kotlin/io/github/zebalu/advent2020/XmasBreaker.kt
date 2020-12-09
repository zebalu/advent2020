package io.github.zebalu.advent2020

class XmasBreaker {
	fun findFirstBadNum(lines :List<String>, size: Int): Int {
		val preamble = Preamble(size)
		for(s in lines) {
			val i = s.toInt()
			if(!preamble.add(i)) {
				return i
			}
		}
		throw IllegalArgumentException("Can not work with inputs")
	}
	
	fun findDecode(lines :List<String>, size: Int): Int {
		val preamble = Preamble(size)
		for(s in lines) {
			val i = s.toInt()
			if(!preamble.add(i)) {
				return preamble.findDecodeFor(i)
			}
		}
		throw IllegalArgumentException("Can not work with inputs")
	}
}