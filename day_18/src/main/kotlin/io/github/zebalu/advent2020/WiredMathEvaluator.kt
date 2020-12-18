package io.github.zebalu.advent2020

class WiredMathEvaluator(private val line: String) {
	private var pointer = 0
	fun evaluate(): Long {
		var nextOperand = '?'
		var result = 0L
		var nextNum = 0L
		while (pointer < line.length) {
			if (line[pointer] == '(') {
				++pointer
				nextNum = evaluate()
				result = applyOperand(result, nextNum, nextOperand)
				nextNum = 0L
				nextOperand = '?'
			} else if (line[pointer] == ')') {
				++pointer
				return result
			} else if (line[pointer] == '+' || line[pointer] == '*') {
				nextOperand = line[pointer]
				++pointer
			} else if (line[pointer] == ' ') {
				++pointer
			} else {
				nextNum = readNum()
				result = applyOperand(result, nextNum, nextOperand)
				nextNum = 0L
				nextOperand = '?'
			}
		}
		return result
	}

	private fun applyOperand(left: Long, right: Long, operand: Char): Long {
		return if (operand == '+') {
			left + right
		} else if (operand == '*') {
			left * right
		} else if (operand == '?') {
			right
		} else {
			throw IllegalArgumentException("what is this? $operand")
		}
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
		if (pointer < line.length) {
			return line.substring(pointer, line.length)
		}
		return ""
	}
}