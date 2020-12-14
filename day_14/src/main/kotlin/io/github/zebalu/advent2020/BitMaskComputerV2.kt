package io.github.zebalu.advent2020

class BitMaskComputerV2 : AbstractBitmaskComputer() {

	override protected fun saveValue(memAddress: String, valueStr: String) {
		getMemoryAddresses(memAddress).forEach { memory[it] = valueStr.toLong().toString(2) }
	}

	private fun getMemoryAddresses(startAddress: String): List<String> {
		val startChars = applyFixBits(startAddress)
		var result = listOf<String>(String(startChars))
		for (i in startChars.indices) {
			if (mask[i] == 'X') {
				result = replaceBit(result, i)
			}
		}
		return result
	}

	private fun applyFixBits(startAddress: String): CharArray {
		val result = extend(startAddress.toLong().toString(2)).toCharArray()
		for (i in mask.indices) {
			if (mask[i] == '1') {
				result[i] = '1'
			}
		}
		return result
	}

	private fun replaceBit(bits: List<String>, bit: Int) = bits.map {
		val toChange = it.toCharArray()
		toChange[bit] = '0'
		val r1 = String(toChange)
		toChange[bit] = '1'
		val r2 = String(toChange)
		listOf<String>(r1, r2)
	}.flatten()


}