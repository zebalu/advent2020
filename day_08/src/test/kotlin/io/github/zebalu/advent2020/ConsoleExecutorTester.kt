package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class ConsoleExecutorTester {

	@Test fun example() {
		Assert.assertEquals(5, ConsoleExecutor.findAccBeforeRepeat(ResourceReader.lines("/example_8.txt")))
	}
	
	@Test fun input() {
		Assert.assertEquals(1200, ConsoleExecutor.findAccBeforeRepeat(ResourceReader.lines("/input_8.txt")))
	}
	
	@Test fun exampleFinish() {
		Assert.assertEquals(8, ConsoleExecutor.makeFinish(ResourceReader.lines("/example_8.txt")))
	}
	
	@Test fun inputFinish() {
		Assert.assertEquals(1023, ConsoleExecutor.makeFinish(ResourceReader.lines("/input_8.txt")))
	}

}