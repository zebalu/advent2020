package io.github.zebalu.advent2020

object ResourceReader {
	fun lines(resName: String): List<String> {
		return ResourceReader::class.java.getResource(resName).readText().lines()
	}
}