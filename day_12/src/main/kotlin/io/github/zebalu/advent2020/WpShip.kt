package io.github.zebalu.advent2020

class WpShip {
	private var y = 0
	private var x = 0
	private var facing = 90
	private var wpX = 10
	private var wpY = -1

	fun move(instructions: List<String>): Int {
		val regex = Regex("(.)(\\d+)")
		//println("start: ${this}")
		instructions.forEach { instruction ->
			val (command, value) = regex.find(instruction)!!.destructured
			//println("doing ${command} with ${value}")
			apply(command, value.toInt())
			//println ("arrive: ${this}")
		}
		//println("finally: ${this}")
		return Math.abs(x) + Math.abs(y)
	}

	private fun apply(command: String, value: Int) {
		when (command) {
			"N" -> wpY -= value
			"S" -> wpY += value
			"E" -> wpX += value
			"W" -> wpX -= value
			"L" -> trunWaypointLeft((360 + value) % 360 )
			"R" -> trunWaypointLeft((360 - value) % 360 )
			"F" -> applyWayPoint(value)
			else -> throw IllegalStateException("UNKNOWN COMMAND: ${command} (${value})")
		}
	}

	private fun applyWayPoint(value: Int) {
		x += wpX * value
		y += wpY * value
	}

	private fun trunWaypointLeft(value: Int) {
		//println("tl: ${value}")
		when (value) {
			90 -> setWayPoints(wpY, -wpX)
			180 -> setWayPoints(-wpX, -wpY)
			270 -> setWayPoints(-wpY, wpX)
			0 -> setWayPoints(wpX, wpY)
			else -> throw IllegalStateException("How to turn left: (${value})?")
		}
	}

	private fun setWayPoints(newWpX: Int, newWpY: Int) {
		wpX = newWpX
		wpY = newWpY
	}
	
	override fun toString(): String = "at: (${x}, ${y})\twp: (${wpX}, ${wpY})"
}