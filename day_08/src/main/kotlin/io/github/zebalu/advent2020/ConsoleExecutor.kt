package io.github.zebalu.advent2020

import java.util.ArrayList
import java.lang.IllegalStateException

object ConsoleExecutor {

	fun findAccBeforeRepeat(code:List<String>): Int {
		val console = GameConsole(code)
		while(!console.isAboutToRepeat()) {
			console.step()
		}
		return console.getAcc()
	}
	
	fun makeFinish(code: List<String>): Int {
		var instruction:Int = 0
		while(instruction<code.size) {
			val changedCode = ArrayList(code)
			val changed =
			if(changedCode[instruction].startsWith("nop")) {
				changedCode[instruction]=changedCode[instruction].replace("nop","jmp")
				true
			} else if(changedCode[instruction].startsWith("jmp")) {
				changedCode[instruction]=changedCode[instruction].replace("jmp","nop")
				true
			} else {
				false
			}
			if(changed) {
				val console = GameConsole(changedCode)
				while(!console.isAboutToRepeat() && !console.isFinished()) {
					console.step()
				}
				if(console.isFinished()) {
					//println ("changed: "+instruction)
					return console.getAcc()
				}
			}
			++instruction
		}
		throw IllegalStateException("Should not happen!")
	}
	
}