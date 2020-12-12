package io.github.zebalu.advent2020

class GameConsole {
	private var pointer:Int = 0
	private var acc:Int=0
	private var code:List<String> = mutableListOf<String>()
	private val visitedNodes = mutableSetOf<Int>()
	private val history = mutableListOf<Int>()
	constructor (code:List<String>) {
		this.code=code
	}
	fun step() {
		visitedNodes.add(pointer)
		history.add(pointer)
		val instruction = code[pointer].split(" ")
		when(instruction[0]) {
			"nop" -> nop()
			"acc" -> acc(instruction[1].toInt())
			"jmp" -> jmp(instruction[1].toInt())
		}
	}
	fun isAboutToRepeat(): Boolean {
		return visitedNodes.contains(pointer)
	}
	fun getAcc():Int {
		return acc
	}
	fun isFinished():Boolean {
		return pointer >= code.size
	}
	private fun nop() {
		++pointer
	}
	private fun acc(change: Int) {
		++pointer
		acc+=change
	}
	private fun jmp(change: Int) {
		pointer+=change
	}
}