package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class YesGroupReaderTest {

	@Test fun countGroupYeses() {
		val groups = ResourceReader.lineGroups("/input_6.txt")
		Assert.assertEquals(6878, YesGroupReader.countYeses(groups))
	}
	
	@Test fun countCommonGroupYeses() {
		val groups = ResourceReader.lineGroups("/input_6.txt")
		Assert.assertEquals(3464, YesGroupReader.countCommonYeses(groups))
	}
}