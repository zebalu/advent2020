package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class WiredMathEvaluatorTest {

	@Test
	fun example1() {
		Assert.assertEquals(71, WiredMathEvaluator("1 + 2 * 3 + 4 * 5 + 6").evaluate())
	}

	@Test
	fun example2() {
		Assert.assertEquals(51, WiredMathEvaluator("1 + (2 * 3) + (4 * (5 + 6))").evaluate())
	}

	@Test
	fun example3() {
		Assert.assertEquals(26, WiredMathEvaluator("2 * 3 + (4 * 5)").evaluate())
	}

	@Test
	fun example4() {
		Assert.assertEquals(437, WiredMathEvaluator("5 + (8 * 3 + 9 + 3 * 4 * 3)").evaluate())
	}

	@Test
	fun example5() {
		Assert.assertEquals(12240, WiredMathEvaluator("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").evaluate())
	}

	@Test
	fun example6() {
		Assert.assertEquals(13632, WiredMathEvaluator("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").evaluate())
	}


}