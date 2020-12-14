package io.github.zebalu.advent2020

object BMComputerReader {
	fun executeComputer(lines: List<String>): Long {
		return BitMaskComputer().followInstructions(lines)
	}
	
	fun executeComputer2(lines: List<String>): Long {
		return BitMaskComputerV2().followInstructions(lines)
	}
}