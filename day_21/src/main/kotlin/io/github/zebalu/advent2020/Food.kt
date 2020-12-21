package io.github.zebalu.advent2020

class Food(line: String) {
	private val ingredients = line.split(" (contains ")[0].split(" ").toSet()
	private val allergenes =
		line.split(" (contains ")[1].split(", ").map { if (it.endsWith(")")) it.substring(0, it.length - 1) else it }
			.toSet()

	fun getAllergeens() = allergenes
	fun getIngridients() = ingredients
	fun containsAllergene(allergene: String) = allergenes.contains(allergene)
	fun containsIngredient(ingredient: String) = ingredients.contains(ingredient)

}