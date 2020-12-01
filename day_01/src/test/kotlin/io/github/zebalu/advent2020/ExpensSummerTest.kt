package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class ExpensSummerTest {

	@Test fun findResult() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(974304, expenseSummer.findMultipliedResult())
	}
	
	@Test fun findResultBy3() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(236430480, expenseSummer.findMultipliedThreeResult())
	}
	
	@Test fun findResultSmart() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(974304, expenseSummer.findMultipliedResultSmart())
	}
	
	@Test fun findResultBy3Smart() {
		val expenseSummer = getExpenseSummer()
		Assert.assertEquals(236430480, expenseSummer.findMultipliedThreeResultSmart())
	}
	
	private fun getExpenseSummer(): ExpensSummer {
		val nums = ResourceReader.lines("/input1.txt").map { s -> Integer.parseInt(s) }
		return ExpensSummer(2020, nums)
	}

}