package io.github.zebalu.advent2020

object WiredMathHomeWorkEvaluator {

	fun evaluate(lines: List<String>): Long {
		var result = 0L
		for (line in lines) {
			result += WiredMathEvaluator(line).evaluate()
		}
		return result;
	}
	
	fun evaluateAdvanced(lines: List<String>): Long {
		var result = 0L
		for (line in lines) {
			result += AdvancedWiredMathEvaluatar(line).evaluate()
		}
		return result;
	}

}