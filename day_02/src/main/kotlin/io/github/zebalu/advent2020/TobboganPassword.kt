package io.github.zebalu.advent2020

class TobboganPassword {
	private val pos1: Int;
	private val pos2: Int;
	private val required: String;
	private val password: String;
	private val valid: Boolean;
	constructor(passwordLine: String) {
		val parts = passwordLine.split(" ");
		val minMax = parts[0].split("-");
		pos1 = Integer.parseInt(minMax[0])-1;
		pos2 = Integer.parseInt(minMax[1])-1;
		required = parts[1].substring(0, parts[1].length-1);
		password = parts[2]
		valid = calculateValidity();
	}
	
	private fun calculateValidity(): Boolean {
		val pos1Has = required.equals(password[pos1]+"")
		val pos2Has = required.equals(password[pos2]+"")
		return pos1Has xor pos2Has;
	}
	
	fun isValid(): Boolean { return valid }
}