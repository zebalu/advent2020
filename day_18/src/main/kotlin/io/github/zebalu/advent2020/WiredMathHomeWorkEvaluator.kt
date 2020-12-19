package io.github.zebalu.advent2020

object WiredMathHomeWorkEvaluator {

	fun evaluate(lines: List<String>) = lines.map { WiredMathEvaluator(it).evaluate() }.sum()

	fun evaluateAdvanced(lines: List<String>) = lines.map { AdvancedWiredMathEvaluatar(it).evaluate() }.sum()

}