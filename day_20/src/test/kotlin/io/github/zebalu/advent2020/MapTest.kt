package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class MapTest {

	@Test
	fun input1() {
		val map = SeeMap(ResourceReader.lineGroups("/input_20.txt"))
		Assert.assertEquals(59187348943703, map.multiplyCornerids())
	}
	
	@Test
	fun input2() {
		val map = SeeMap(ResourceReader.lineGroups("/input_20.txt"))
		val patch = Patch(ResourceReader.lines("/dragon.txt"))
		Assert.assertEquals(1565, map.countRoughWater(patch))
	}
}