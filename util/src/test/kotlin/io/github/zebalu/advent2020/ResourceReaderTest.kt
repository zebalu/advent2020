package io.github.zebalu.adven2020

import kotlin.test.assertEquals
import org.junit.Test as test
import io.github.zebalu.advent2020.ResourceReader

class ResourceReaderTest {
	@test fun readsAllLines() {
		val lines = ResourceReader.lines("/test_1.txt")
		assertEquals(3, lines.size)
	}
}