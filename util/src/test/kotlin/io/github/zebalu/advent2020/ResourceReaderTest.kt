package io.github.zebalu.adven2020

import kotlin.test.assertEquals
import org.junit.Test as test
import io.github.zebalu.advent2020.ResourceReader

class ResourceReaderTest {
	@test fun readsAllLines() {
		val lines = ResourceReader.lines("/test_1.txt")
		assertEquals(3, lines.size)
	}
	@test fun readGroups() {
		val groups = ResourceReader.lineGroups("/test_2.txt")
		assertEquals(2, groups.size)
		assertEquals(2, groups[0].size)
		assertEquals(3, groups[1].size)
	}
}