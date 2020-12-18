package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class AdvancedWiredMathEvaluatorTest {
	@Test
	fun example1() {
		Assert.assertEquals(231, AdvancedWiredMathEvaluatar("1 + 2 * 3 + 4 * 5 + 6").evaluate())
	}
	
	@Test
	fun example2() {
		Assert.assertEquals(51, AdvancedWiredMathEvaluatar("1 + (2 * 3) + (4 * (5 + 6))").evaluate())
	}
	
	@Test
	fun example3() {
		Assert.assertEquals(46, AdvancedWiredMathEvaluatar("2 * 3 + (4 * 5)").evaluate())
	}
	
	@Test
	fun example4() {
		Assert.assertEquals(1445, AdvancedWiredMathEvaluatar("5 + (8 * 3 + 9 + 3 * 4 * 3)").evaluate())
	}
	
	@Test
	fun example5() {
		Assert.assertEquals(669060, AdvancedWiredMathEvaluatar("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").evaluate())
	}
	
	@Test
	fun example6() {
		Assert.assertEquals(23340, AdvancedWiredMathEvaluatar("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").evaluate())
	}
	
}