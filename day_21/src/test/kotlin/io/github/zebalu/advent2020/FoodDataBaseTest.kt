package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class FoodDataBaseTest {
	@Test
	fun input1() {
		val fdb = FoodDataBase(ResourceReader.lines("/input_21.txt"))
		Assert.assertEquals(2635, fdb.countAppearanceOfSafeIngredients())
	}
	@Test
	fun input2() {
		val fdb = FoodDataBase(ResourceReader.lines("/input_21.txt"))
		Assert.assertEquals("xncgqbcp,frkmp,qhqs,qnhjhn,dhsnxr,rzrktx,ntflq,lgnhmx", fdb.canonincalDangeousIngredients())
	}
}