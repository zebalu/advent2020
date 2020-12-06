package io.github.zebalu.advent2020

object ResourceReader {
	fun lines(resName: String): List<String> {
		return ResourceReader::class.java.getResource(resName).readText().lines()
	}
	
	fun lineGroups(resName: String): List<List<String>> {
		val result = ArrayList<List<String>>()
		val group = ArrayList<String>()
		lines(resName).forEach { line ->
			if(line.isEmpty()) {
				result.add(ArrayList(group))
				group.clear()
			} else {
				group.add(line)
			}
		}
		if(!group.isEmpty()) {
			result.add(group)
		}
		return result
	}
}