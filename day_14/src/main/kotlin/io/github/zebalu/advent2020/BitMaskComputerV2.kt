package io.github.zebalu.advent2020

class BitMaskComputerV2 {

	private var mask: String = ""
	private val memory = mutableMapOf<String, String>()

	fun followInstructions(instructions: List<String>): Long {
		val regex = Regex("^([^\\d]+)(\\d+)([^\\d]+)(\\d+)$")
		for (instruction in instructions) {
			if (instruction.startsWith("mask")) {
				saveNewMask(instruction)
			} else {
				val (_, memAddress, _, valueStr) = regex.find(instruction)!!.destructured
				getMemoryAddresses(memAddress).forEach { memory[it] = valueStr.toLong().toString(2) }
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

	private fun getMemoryAddresses(startAddress: String): List<String> {
		val converted = extend(startAddress.toLong().toString(2))
		val startChars = CharArray(36) { i -> converted[i] }
		for (i in mask.indices) {
			when (mask[i]) {
				'1' -> startChars[i] = '1'
			}
		}
		val result = mutableListOf<String>()
		result.add(String(startChars))
		for (i in startChars.indices) {
			if (mask[i] == 'X') {
				val newres = result.map { s ->
					val toChange = s.toCharArray()
					toChange[i] = '0'
					val r1 = String(toChange)
					toChange[i] = '1'
					val r2 = String(toChange)
					listOf<String>(r1, r2)
				}.flatten()
				result.clear()
				result.addAll(newres)
			}
		}
		return result
	}

}