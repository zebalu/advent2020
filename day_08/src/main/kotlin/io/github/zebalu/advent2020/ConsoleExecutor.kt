package io.github.zebalu.advent2020

import java.util.ArrayList
import java.lang.IllegalStateException

object ConsoleExecutor {

	fun findAccBeforeRepeat(code: List<String>): Int {
		val console = GameConsole(code)
		while (!console.isAboutToRepeat()) {
			console.step()
		}
		return console.getAcc()
	}

	fun makeFinish(code: List<String>): Int {
		for (instruction in code.indices) {
			val (changedCode, changed) = changeCode(code.toMutableList(), instruction)
			if (changed) {
				val console = GameConsole(changedCode)
				while (!console.isAboutToRepeat() && !console.isFinished()) {
					console.step()
				}
				if (console.isFinished()) {
					return console.getAcc()
				}
			}
		}
		throw IllegalStateException("Should not happen!")
	}

	fun changeCode(code: MutableList<String>, idx: Int): Pair<List<String>, Boolean> {
		return Pair(
			code,
			if (code[idx].startsWith("nop"))
				true.also { code[idx] = code[idx].replace("nop", "jmp") }
			else if (code[idx].startsWith("jmp"))
				true.also { code[idx] = code[idx].replace("jmp", "nop") }
			else false
		)
	}

}