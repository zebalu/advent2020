package io.github.zebalu.advent2020

class TobboganPassword {
	private val pos1: Int;
	private val pos2: Int;
	private val required: Char;
	private val password: String;
	private val valid: Boolean;
	constructor(passwordLine: String) {
		val parts = passwordLine.split(" ");
		val positions = parts[0].split("-");
		pos1 = Integer.parseInt(positions[0])-1;
		pos2 = Integer.parseInt(positions[1])-1;
		required = parts[1][0];
		password = parts[2]
		valid = calculateValidity();
	}
	
	private fun calculateValidity(): Boolean = (required == password[pos1]) xor (required == password[pos2])
	
	fun isValid(): Boolean = valid 
}