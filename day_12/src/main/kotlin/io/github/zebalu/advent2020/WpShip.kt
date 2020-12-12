package io.github.zebalu.advent2020

class WpShip : AbstractShip() {
	private var wpX = 10
	private var wpY = -1

	override protected fun apply(command: String, value: Int) {
		when (command) {
			"N" -> wpY -= value
			"S" -> wpY += value
			"E" -> wpX += value
			"W" -> wpX -= value
			"L" -> trunWaypointLeft((360 + value) % 360)
			"R" -> trunWaypointLeft((360 - value) % 360)
			"F" -> x += wpX * value.also { y += wpY * value }
			else -> throw IllegalStateException("UNKNOWN COMMAND: ${command} (${value})")
		}
	}

	private fun trunWaypointLeft(value: Int) {
		when (value) {
			90 -> wpX = wpY.also { wpY = -wpX }
			180 -> wpX = -wpX.also { wpY = -wpY }
			270 -> wpX = -wpY.also { wpY = wpX }
			0 -> wpX = wpX.also { wpY = wpY }
			else -> throw IllegalStateException("How to turn left: (${value})?")
		}
	}

	override fun toString(): String = "at: (${x}, ${y})\twp: (${wpX}, ${wpY})"
}