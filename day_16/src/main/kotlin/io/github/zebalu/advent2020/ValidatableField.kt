package io.github.zebalu.advent2020

class ValidatableField(private val name: String, private val lower: IntRange, private val upper: IntRange) {

	fun accepts(num: Int): Boolean {
		return lower.contains(num) || upper.contains(num)
	}

	override fun toString(): String = "$name: $lower $upper"
	
	fun getName() = name
}