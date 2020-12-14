package io.github.zebalu.advent2020

abstract class AbstractBitmaskComputer {
	protected var mask = ""
	protected val memory = mutableMapOf<String, String>()

	fun followInstructions(instructions: List<String>): Long {
		val regex = Regex("^([^\\d]+)(\\d+)([^\\d]+)(\\d+)$")
		for (instruction in instructions) {
			if (instruction.startsWith("mask")) {
				saveNewMask(instruction)
			} else {
				val (_, memAddress, _, valueStr) = regex.find(instruction)!!.destructured
				saveValue(memAddress, valueStr)
			}
		}
		return memory.values.map { it.toLong(2) }.sum()
	}
	
	protected abstract fun saveValue(memAddress: String, valueStr:String)

	protected fun extend(bits: String): String {
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