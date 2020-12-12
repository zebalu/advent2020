package io.github.zebalu.advent2020

abstract class AbstractShip {
    protected var y = 0
	protected var x = 0

	fun move(instructions: List<String>): Int {
		val regex = Regex("(.)(\\d+)")
		instructions.forEach { instruction ->
			val (command, value) = regex.find(instruction)!!.destructured
			apply(command, value.toInt())
		}
		return Math.abs(x) + Math.abs(y)
	}

	abstract protected fun apply(command: String, value: Int)
}