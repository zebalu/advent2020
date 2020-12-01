package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class SmartExpensSummerTest {
	@Test fun findResult() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(974304, expenseSummer.findMultipliedResult())
	}
	
	@Test fun findResultBy3() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(236430480, expenseSummer.findMultipliedThreeResult())
	}
	
	
	private fun getExpenseSummer(): SmartExpensSummer {
		val nums = ResourceReader.lines("/input1.txt").map { s -> Integer.parseInt(s) }
		return SmartExpensSummer(2020, nums)
	}
}