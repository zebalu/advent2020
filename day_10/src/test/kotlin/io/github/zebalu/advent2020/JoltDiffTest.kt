package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class JoltDiffTest {

	@Test
	fun example1() {
		val ints = ResourceReader.lines("/example_10_1.txt").map { l -> l.toInt() }
		Assert.assertEquals(35, JoltOrder(ints).countDiffs())
	}
	
	@Test
	fun example2() {
		val ints = ResourceReader.lines("/example_10_2.txt").map { l -> l.toInt() }
		Assert.assertEquals(220, JoltOrder(ints).countDiffs())
	}
	
	@Test
	fun input1() {
		val ints = ResourceReader.lines("/input_10.txt").map { l -> l.toInt() }
		Assert.assertEquals(2664, JoltOrder(ints).countDiffs())
	}
	
	@Test
	fun example1_2() {
		val ints = ResourceReader.lines("/example_10_1.txt").map { l -> l.toInt() }
		Assert.assertEquals(8, JoltOrder(ints).countWays())
	}
	
	@Test
	fun input2() {
		val ints = ResourceReader.lines("/input_10.txt").map { l -> l.toInt() }
		//println(JoltOrder(ints).countWays())
		Assert.assertEquals(148098383347712, JoltOrder(ints).countWays())
	}
}