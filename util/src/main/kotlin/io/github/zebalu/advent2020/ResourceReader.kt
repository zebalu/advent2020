package io.github.zebalu.advent2020

object ResourceReader {
	fun lines(resName: String): List<String> {
		return getText(resName).lines()
	}

	fun lineGroups(resName: String): List<List<String>> {
		return splitToLineGroupsByEmptyLines(getText(resName)).map { g -> g.lines() }
	}

	private fun getText(resName: String): String {
		return ResourceReader::class.java.getResource(resName).readText()
	}

	private fun getSeparator(text: String): String {
		return if (text.contains("\r\n")) "\r\n"
		else if (text.contains("\n\r")) "\n\r"
		else if (text.contains("\n")) "\n"
		else throw IllegalStateException("Separator can not be determined")
	}

	private fun splitToLineGroupsByEmptyLines(text: String): List<String> {
		return splitToLineGroupsByEmptyLines(text, getSeparator(text))
	}

	private fun splitToLineGroupsByEmptyLines(text: String, separator: String): List<String> {
		return text.split(separator + separator)
	}
}