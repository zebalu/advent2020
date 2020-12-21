package io.github.zebalu.advent2020

class FoodDataBase(lines: List<String>) {

	private val foods = lines.map { Food(it) }
	private val allIngredients = foods.map { it.getIngridients() }.flatten().toSet()
	private val allAllergenes = foods.map { it.getAllergeens() }.flatten().toSet()
	private val ingredientFoodMap = allIngredients.map { i -> i to foods.filter { it.containsIngredient(i) } }.toMap()
	private val allergeneFoodMap = allAllergenes.map { a -> a to foods.filter { it.containsAllergene(a) } }.toMap()
	private val allergeneIngridientMap = mapAllergenesToIngrediens()

	fun countAppearanceOfSafeIngredients() =
		(allIngredients - allergeneIngridientMap.values).map { ingredientFoodMap[it]!!.size }.sum()


	fun canonincalDangeousIngredients() = allergeneIngridientMap.keys.sorted().map { allergeneIngridientMap[it]!! }
		.reduce { acc, next -> acc + "," + next }

	private fun mapAllergenesToIngrediens(): Map<String, String> {
		val remainging = allAllergenes.toMutableSet()
		val allergeneToIngridientMap = mutableMapOf<String, String>()
		val mappedIngridients = mutableSetOf<String>()
		while (!remainging.isEmpty()) {
			remainging.forEach { allergene ->
				val possibleIngredients =
					allergeneFoodMap[allergene]?.map { it.getIngridients() - mappedIngridients }
						?.reduce { acc, next -> acc.intersect(next) }
				if (possibleIngredients?.size == 1) {
					mappedIngridients += possibleIngredients
					allergeneToIngridientMap[allergene] = possibleIngredients.first()
				}
			}
			remainging -= allergeneToIngridientMap.keys
		}
		return allergeneToIngridientMap
	}
}