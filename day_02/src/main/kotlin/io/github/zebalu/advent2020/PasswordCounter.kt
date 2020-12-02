package io.github.zebalu.advent2020

object PasswordCounter {
	fun countValidByBasicRules(lines: List<String>): Int {
		return lines.map { s -> Password(s) }.filter { p -> p.isValid() }.count()
	}
	
	fun countValidByTobboganRules(lines: List<String>): Int {
		return lines.map { s -> TobboganPassword(s) }.filter { p -> p.isValid() }.count()
	}
}