package io.github.zebalu.advent2020

import java.util.Collections

class Passport {
	var byr: String? = null
	var iyr: String? = null
	var eyr: String? = null
	var hgt: String? = null
	var hcl: String? = null
	var ecl: String? = null
	var pid: String? = null
	var cid: String? = null

	companion object {
		val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
		fun isValidEyeColor(eyeColor: String): Boolean {
			return validEyeColors.contains(eyeColor)
		}
	}

	constructor(fields: List<String>) {
		for (field in fields) {
			val vals = field.split(':')
			when (vals[0]) {
				"byr" -> byr = vals[1]
				"iyr" -> iyr = vals[1]
				"eyr" -> eyr = vals[1]
				"hgt" -> hgt = vals[1]
				"hcl" -> hcl = vals[1]
				"ecl" -> ecl = vals[1]
				"pid" -> pid = vals[1]
				"cid" -> cid = vals[1]
				else -> print("problem:\t$field\t$vals")
			}
		}
	}

	fun isRealValid(): Boolean {
		return countNotNulls() == 8
	}

	fun isCheatedValid(): Boolean {
		return countNotNulls() == 7
	}

	fun isCheatedExtendedValid(): Boolean {
		return countExtendedValid() == 7
	}

	fun countExtendedValid(): Int {
		var result = 0
		result += byr?.let { s -> s.toInt() }.let { year -> if (year in 1920..2002) 1 else 0 }
		result += iyr?.let { s -> s.toInt() }.let { year -> if (year in 2010..2020) 1 else 0 }
		result += eyr?.let { s -> s.toInt() }.let { year -> if (year in 2020..2030) 1 else 0 }
		result += hgt?.let { s -> if (isValidHeight(s)) 1 else 0} ?: 0
		result += hcl?.let { s -> if (isValidHairColor(s)) 1 else 0 } ?: 0
		result += ecl?.let { c -> if (isValidEyeColor(c)) 1 else 0 } ?: 0
		result += pid?.let { p -> if (p.length == 9) 1 else 0 } ?: 0
		//if (cid != null) ++result
		return result
	}

	private fun countNotNulls(): Int {
		var result = 0
		if (byr != null) ++result
		if (iyr != null) ++result
		if (eyr != null) ++result
		if (hgt != null) ++result
		if (hcl != null) ++result
		if (ecl != null) ++result
		if (pid != null) ++result
		return result
	}

	private fun isValidHeight(height: String): Boolean {
		return if (height.endsWith("cm")) height.substring(0, height.length - 2).toInt() in 150..193
		else if (height.endsWith("in")) height.substring(0, height.length - 2).toInt() in 59..76
		else false
	}

	private fun isValidHairColor(hairColor: String): Boolean {
		if (hairColor.length == 7) {
			try {
				hairColor.substring(1).toLong(16)
				return true
			} catch (e: Exception) {
				return false
			}
		}
		return false
	}

	override fun toString(): String {
		return "byr: ${byr}, iyr: ${iyr}, eyr: ${eyr}, hgt: ${hgt}, hcl: ${hcl}, ecl: ${ecl}, pid: ${pid}, cid: ${cid}"
	}
}