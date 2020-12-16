package io.github.zebalu.advent2020

class Ticket(private val fields: List<ValidatableField>) {

	fun isValid(nums: List<Int>): Pair<Boolean, List<Int>> {
		val reamining = fields.toMutableSet()
		val used = mutableSetOf<ValidatableField>()
		val invalids = mutableListOf<Int>()
		for (num in nums) {
			val found = findMatchingFiled(num, reamining)
			if (found != null) {
				reamining -= found
				used += found
			} else {
				invalids += num
			}
		}
		return Pair(invalids.isEmpty(), invalids)
	}

	fun validOnPosition(num: Int, pos: Int): Boolean {
		return fields[pos].accepts(num)
	}
	
	fun getField(pos: Int): ValidatableField {
		return fields[pos]
	}

	private fun findMatchingFiled(num: Int, fields: Set<ValidatableField>): ValidatableField? {
		for (field in fields) {
			if (field.accepts(num)) {
				return field
			}
		}
		return null
	}
}