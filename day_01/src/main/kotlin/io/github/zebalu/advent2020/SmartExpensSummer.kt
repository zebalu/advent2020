package io.github.zebalu.advent2020

class SmartExpensSummer {
	val target: Int;
	val nums: List<Int>;

	constructor(target: Int, nums: List<Int>) {
		this.target = target;
		this.nums = ArrayList(nums)
		this.nums.sort()
	}

	fun findMultipliedResult(): Int {
		var i = 0
		while (i < nums.size && nums[i] <= target) {
			val modifiedTarget = target - nums[i]
			var j = 0
			while (j < nums.size && nums[j] <= modifiedTarget) {
				if (nums[j] == modifiedTarget) {
					return nums[i] * modifiedTarget
				}
				++j
			}
			++i
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}

	fun findMultipliedThreeResult(): Int {
		var i = 0
		while (i < nums.size && nums[i] <= target) {
			val modifiedTarget = target - nums[i]
			var j = 0
			while (j < nums.size && nums[j] <= modifiedTarget) {
				val modTarget2 = modifiedTarget - nums[j]
				var k = 0
				while(k<nums.size && nums[k] <= modTarget2) {
					
				if (nums[k] == modTarget2) {
					return nums[i] * nums[j] * nums[k]
				}
					++k
				}
				++j
			}
			++i
		}
		throw NoSuchElementException("Can not find such elements that sums up to " + target);
	}
}