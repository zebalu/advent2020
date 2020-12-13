package io.github.zebalu.advent2020

import java.math.BigInteger

object IdealTimeFinder {

	fun findTime(buses: List<Pair<Int, Int>>): Long = longBuses(buses)
		.fold(Pair(0L, 1L)) { acc, next -> applyBus(acc.first, acc.second, next.first, next.second) }.first

	private fun applyBus(sum: Long, interval: Long, bus: Long, timeDif: Long) =
		Pair(findTimeWithRightDiff(sum, interval, timeDif, bus), interval * bus)

	private tailrec fun findTimeWithRightDiff(start: Long, interval: Long, expected: Long, bus: Long): Long =
		if ((start + expected) % bus == 0L) start
		else findTimeWithRightDiff(start + interval, interval, expected, bus)

	private fun longBuses(buses: List<Pair<Int, Int>>) = buses.map { Pair(it.first.toLong(), it.second.toLong()) }

}