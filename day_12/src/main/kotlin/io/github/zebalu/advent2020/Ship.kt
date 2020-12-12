package io.github.zebalu.advent2020

import java.lang.IllegalStateException

class Ship : AbstractShip() {
	private var facing = 90

	override protected fun apply(command: String, value: Int) {
		when (command) {
			"N" -> y -= value
			"S" -> y += value
			"E" -> x += value
			"W" -> x -= value
			"L" -> facing = (360 + facing - value) % 360
			"R" -> facing = (360 + facing + value) % 360
			"F" -> when (facing) {
				90 -> apply("E", value)
				180 -> apply("S", value)
				270 -> apply("W", value)
				0 -> apply("N", value)
				else -> throw IllegalStateException("How to go to: ${facing} (${value})?")
			}
			else -> throw IllegalStateException("UNKNOWN COMMAND: ${command} (${value})")
		}
	}
}