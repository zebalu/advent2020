package io.github.zebalu.advent2020

class Password {
	private val min: Int;
	private val max: Int;
	private val required: String;
	private val password: String;
	private val valid: Boolean;
	constructor(passwordLine: String) {
		val parts = passwordLine.split(" ");
		val minMax = parts[0].split("-");
		min = Integer.parseInt(minMax[0]);
		max = Integer.parseInt(minMax[1]);
		required = parts[1].substring(0, parts[1].length-1);
		password = parts[2]
		valid = calculateValidity();
	}
	
	private fun calculateValidity(): Boolean {
		var count = 0
		for(c in password) {
			val cs: String = ""+c;
			if (cs.equals(required)) {
				++count
			}
		}
		return min <= count && count <= max;
	}
	
	fun isValid(): Boolean { return valid }
}