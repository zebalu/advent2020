package io.github.zebalu.advent2020

class BitMaskComputer() {

	private var mask: String = ""
	private val memory = mutableMapOf<String, String>()

	fun followInstructions(instructions: List<String>): Long {
		val regex = Regex("^([^\\d]+)(\\d+)([^\\d]+)(\\d+)$")
		for (instruction in instructions) {
			if (instruction.startsWith("mask")) {
				saveNewMask(instruction)
			} else {
				val (_, memAddress, _, valueStr) = regex.find(instruction)!!.destructured
				memory[memAddress] = applyMask(valueStr)
			}
		}
		return memory.values.map { it.toLong(2) }.sum()
	}

	private fun applyMask(num: String): String {
		val incoming = extend(num.toLong().toString(2))
		val result = CharArray(36) { i -> incoming[i] }
		for (i in result.indices) {
			when (mask[i]) {
				'X' -> result[i] = incoming[i]
				'0' -> result[i] = '0'
				'1' -> result[i] = '1'
				else -> throw IllegalStateException("Unknows charachter: ${mask[i]}")
			}
		}
		return String(result)
	}

	private fun extend(bits: String): String {
		var result = ""
		while (result.length < 36 - bits.length) {
			result += "0"
		}
		return result + bits
	}

	private fun saveNewMask(maskInstruction: String) {
		mask = maskInstruction.substring(7)
	}

}