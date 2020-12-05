package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class BoardingPassTest {

	@Test
	fun exampleId0() {
		Assert.assertEquals(357, BoardingPass("FBFBBFFRLR").getId())
	}

	@Test
	fun exampleId1() {
		Assert.assertEquals(567, BoardingPass("BFFFBBFRRR").getId())
	}

	@Test
	fun exampleId2() {
		Assert.assertEquals(119, BoardingPass("FFFBBBFRRR").getId())
	}

	@Test
	fun exampleId3() {
		Assert.assertEquals(820, BoardingPass("BBFFBBFRLL").getId())
	}
}