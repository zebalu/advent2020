package io.github.zebalu.advent2020

import java.util.ArrayList

object YesGroupReader {
	fun readGroups(lines: List<String>): List<List<String>> {
		val result = ArrayList<List<String>>()
		var subGroup = ArrayList<String>()
		for(line in lines) {
			if(line.isEmpty()) {
				result.add(subGroup)
				subGroup = ArrayList()
			} else {
				subGroup.add(line)
			}
		}
		if(!subGroup.isEmpty()){
			result.add(subGroup)
		}
		return result
	}
	
	fun countYeses(groups: List<List<String>>): Int {
		return groups.map { g -> countGroupYeses(g) }.sum()
	}
	
	fun countCommonYeses(groups: List<List<String>>): Int {
		return groups.map { g -> countCommonGroupYeses(g) }.sum()
	}
	
	private fun countGroupYeses(group: List<String>): Int {
		val set = HashSet<Char>()
		for(member in group) {
			for(answer in member) {
				set.add(answer)
			}
		}
		return set.size
	}
	
	private fun countCommonGroupYeses(group: List<String>): Int {
		var set = lineToSet(group[0])
		var i = 1
		while (i<group.size && set.size >0) {
			set = set.intersect(lineToSet(group[i]))
			++i
		}
		return set.size
	}
	
	private fun lineToSet(line: String): Set<Char> {
		val result = HashSet<Char>()
		for(c in line) {
			result.add(c)
		}
		return result
	}
}