package io.github.zebalu.advent2020

abstract class AbstractWiredMathEvaluator(private val line: String, private val isAdvanced: Boolean = false) {
	private var pointer = 0
	fun evaluate(): Long {
		var nextOperand = '?'
		var result = 0L
		while (pointer < line.length) {
			if (line[pointer] == '(') {
				++pointer
				result = applyOperand(result, evaluate(), nextOperand)
				nextOperand = '?'
			} else if (line[pointer] == ')') {
				++pointer
				return result
			} else if (line[pointer] == '+' || line[pointer] == '*') {
				nextOperand = line[pointer]
				++pointer
				if (isAdvanced && nextOperand == '*') {
					return applyOperand(result, evaluate(), nextOperand)
				}
			} else if (line[pointer] == ' ') {
				++pointer
			} else {
				result = applyOperand(result, readNum(), nextOperand)
				nextOperand = '?'
			}
		}
		return result
	}

	private fun applyOperand(left: Long, right: Long, operand: Char): Long {
		var result = 0L
		if (operand == '+') {
			result = left + right
		} else if (operand == '*') {
			result = left * right
		} else if (operand == '?') {
			result = right
		}
		return result
	}

	fun readNum(): Long {
		var num = ""
		while (pointer < line.length && line[pointer].toInt() in '0'.toInt()..'9'.toInt()) {
			num = num + line[pointer]
			++pointer
		}
		return num.toLong()
	}

	override fun toString(): String = beforeStr() + inStr() + afterStr()

	private fun beforeStr(): String {
		if (pointer > 0) {
			return line.substring(0, pointer)
		}
		return ""
	}

	private fun inStr(): String {
		if (pointer < line.length) {
			return "[${line[pointer]}]"
		}
		return "[]"
	}

	private fun afterStr(): String {
		if (pointer < line.length - 1) {
			return line.substring(pointer + 1, line.length)
		}
		return ""
	}

}
