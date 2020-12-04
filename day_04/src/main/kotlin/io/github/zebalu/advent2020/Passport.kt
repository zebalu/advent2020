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
				else -> print("problem: " + field + "\t" + vals)
			}
		}
	}

	fun isRealValid(): Boolean {
		return countNotNulls() == 8
	}

	fun isCheatedValid(): Boolean {
		return countNotNulls() == 8 || countNotNulls() == 7 && cid == null
	}

	fun isCheatedExtendedValid(): Boolean {
		return countExtendedValid() == 7
	}

	fun countExtendedValid(): Int {
		var result = 0
		if (byr != null) {
			val year = Integer.parseInt(byr)
			if (1920 <= year && year <= 2002) ++result
		}
		if (iyr != null) {
			val year = Integer.parseInt(iyr)
			if (2010 <= year && year <= 2020) ++result
		}
		if (eyr != null) {
			val year = Integer.parseInt(eyr)
			if (2020 <= year && year <= 2030) ++result
		}
		if (hgt != null) {
			val fixHgt = hgt
			if (fixHgt != null) {
				val isCm = fixHgt.endsWith("cm")
				val isIn = fixHgt.endsWith("in")
				if (isCm || isIn) {
					val cutHgt = fixHgt.substring(0, fixHgt.length - 2)
					val height = cutHgt.toInt()
					if (isCm) {
						if (150 <= height && height <= 193) {
							++result
						}
					} else {
						if (59 <= height && height <= 76) {
							++result
						}
					}
				}
			}
		}
		if (hcl != null) {
			val hairColor = hcl
			if (hairColor != null) {
				if (hairColor.length == 7) {
					try {
						hairColor.substring(1).toLong(16)
						++result
					} catch (e: Exception) {

					}
				}
			}
		}
		if (ecl != null) {
			val eye = ecl
			if (eye != null) {
				if (isValidEyeColor(eye)) {
					++result
				}
			}
		}
		if (pid != null) {
			val fixPid = pid
			if (fixPid != null) {
				if (fixPid.length == 9) {
					try {
						fixPid.toLong()
						++result
					} catch (e: Exception) {
						
					}
				}
			}
		}
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
		if (cid != null) ++result
		return result
	}

	override fun toString(): String {
		return "byr: ${byr}, iyr: ${iyr}, eyr: ${eyr}, hgt: ${hgt}, hcl: ${hcl}, ecl: ${ecl}, pid: ${pid}, cid: ${cid}"
	}
}