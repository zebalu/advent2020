package io.github.zebalu.advent2020

object PasswordCounter {
	fun countValidByBasicRules(lines: List<String>): Int = lines.map { s -> Password(s) }.count { it.isValid() }

	fun countValidByTobboganRules(lines: List<String>): Int =
		lines.map { s -> TobboganPassword(s) }.count { it.isValid() }

}