package io.github.zebalu.advent2020

class BusFinder(private val now: Int, private val buses: List<Int>) {

	val max = buses.maxOrNull()!!

	fun nextIdTimeminutes(): Int {
		val (bus, minute) = nextAvailable()
		return (minute - now) * bus
	}

	private fun nextAvailable(): Pair<Int, Int> {
		for (minute in now..(now + max)) {
			buses.forEach { bus ->
				if (minute % bus == 0) {
					return Pair(bus, minute)
				}
			}
		}
		throw IllegalStateException("There is no next available bus")
	}
}
