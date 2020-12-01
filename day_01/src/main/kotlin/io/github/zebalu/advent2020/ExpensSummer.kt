package io.github.zebalu.advent2020

import java.util.NoSuchElementException

class ExpensSummer {
	val target: Int;
	val nums: List<Int>;

	constructor(target: Int, nums: List<Int>) {
		this.target = target;
		this.nums = nums;
	}

	fun findMultipliedResult(): Int {
		for (i in nums) {
			for (j in nums) {
				if (i + j == target) {
					return i * j;
				}
			}
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}

	fun findMultipliedThreeResult(): Int {
		for (i in nums) {
			for (j in nums) {
				for (k in nums) {
					if (i + j + k == target) {
						return i * j * k;
					}
				}
			}
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}

	fun findMultipliedResultSmart(): Int {
		for (i in nums) {
			if (i < target) {
				for (j in nums) {
					if (j < target) {
						if (i + j == target) {
							return i * j;
						}
					}
				}
			}
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}

	fun findMultipliedThreeResultSmart(): Int {
		for (i in nums) {
			if (i < target) {
				for (j in nums) {
					if (i + j < target) {
						for (k in nums) {
							if (i + j + k == target) {
								return i * j * k;
							}
						}
					}
				}
			}
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}
}