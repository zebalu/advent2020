package io.github.zebalu.advent2020

class Password {
	private val min: Int;
	private val max: Int;
	private val required: Char;
	private val password: String;
	private val valid: Boolean;

	constructor(passwordLine: String) {
		val parts = passwordLine.split(" ");
		val minMax = parts[0].split("-");
		min = Integer.parseInt(minMax[0]);
		max = Integer.parseInt(minMax[1]);
		required = parts[1].substring(0, parts[1].length - 1)[0];
		password = parts[2]
		valid = calculateValidity();
	}

	private fun calculateValidity(): Boolean = password.count { c -> c == required } in min..max

	fun isValid(): Boolean = valid
}