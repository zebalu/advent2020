package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class WiredMathHomeWorkEvaluatorTest {
	@Test
	fun input1() {
		Assert.assertEquals(21993583522852, WiredMathHomeWorkEvaluator.evaluate(ResourceReader.lines("/input_18.txt")))
	}
	
	@Test
	fun input2() {
		Assert.assertEquals(122438593522757, WiredMathHomeWorkEvaluator.evaluateAdvanced(ResourceReader.lines("/input_18.txt")))
	}
}