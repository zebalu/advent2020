package io.github.zebalu.advent2020

class BitMaskComputer() : AbstractBitmaskComputer() {

	override protected fun saveValue(memAddress: String, valueStr: String) {
		memory[memAddress] = applyMask(valueStr)
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

}