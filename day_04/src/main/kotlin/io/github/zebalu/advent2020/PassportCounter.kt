package io.github.zebalu.advent2020

object PassportCounter {
	fun countFakeValid(lines: List<String>): Int {
		val passports = PassportReader.readFields(lines).map { fields->Passport(fields) }
		return passports.count { p -> p.isCheatedValid() }
	}
	
	fun countExtendedFakeValid(lines: List<String>): Int {
		val passports = PassportReader.readFields(lines).map { fields->Passport(fields) }
		return passports.count { p -> p.isCheatedExtendedValid() }
	}
}