package io.github.zebalu.advent2020

object PassportReader {
	
	fun readFields(lines: List<String>): List<List<String>> {
		val result: MutableList<List<String>> = ArrayList()
		var actualList: MutableList<String> = ArrayList()
		for(line in lines) {
			if(line.isBlank()) {
				result.add(actualList)
				actualList = ArrayList()
			} else {
				actualList.addAll(line.split(" "))
			}
		}
		if(!actualList.isEmpty()) {
			result.add(actualList)
		}
		return result
	}
}