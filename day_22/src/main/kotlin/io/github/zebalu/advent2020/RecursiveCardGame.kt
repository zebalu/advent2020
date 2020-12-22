package io.github.zebalu.advent2020

class RecursiveCardGame(lines: List<List<String>>) {
	val player1 = lines[0].drop(1).map { it.toInt() }.toMutableList()
	val player2 = lines[1].drop(1).map { it.toInt() }.toMutableList()
	val deckHistory = mutableSetOf<Pair<List<Int>, List<Int>>>()

	fun calculateScore(): Int {
		while (canGoOn()) {
			playRound()
		}
		val winner = (if (player1.size > player2.size) player1 else player2)
		return winner.mapIndexed { idx, value -> (winner.size - idx) * value }.sum()
	}

	private fun playRound() {
		var state = Pair(copy(player1), copy(player2.toList()))
		if (canGoOn(state)) {
			val p1 = draw(player1)
			val p2 = draw(player2)
			if (p1 <= player1.size && p2 <= player2.size)
				evalSubGame(
					p1,
					player1,
					player2,
					subGame(p1, p2, player1.take(p1).toMutableList(), player2.take(p2).toMutableList())
				)
			else if (p1 > p2)
				insert(p1, p2, player1)
			else
				insert(p2, p1, player2)
			deckHistory.add(state)
		}
	}

	private fun subGame(p1: Int, p2: Int, pl1: MutableList<Int>, pl2: MutableList<Int>): List<Int> {
		val gameHistory = mutableSetOf<Pair<List<Int>, List<Int>>>()
		while (pl1.size > 0 && pl2.size > 0) {
			val state = Pair(copy(pl1), copy(pl2))
			if (gameHistory.contains(state)) return listOf(p1, p2)
			gameHistory.add(state)
			val d1 = draw(pl1)
			val d2 = draw(pl2)
			if (d1 <= pl1.size && d2 <= pl2.size)
				evalSubGame(d1, pl1, pl2, subGame(d1, d2, pl1.take(d1).toMutableList(), pl2.take(d2).toMutableList()))
			else if (d1 > d2)
				insert(d1, d2, pl1)
			else
				insert(d2, d1, pl2)
		}
		return if (pl2.isEmpty())
			listOf(p1, p2)
		else
			listOf(p2, p1)
	}

	private fun draw(list: MutableList<Int>): Int {
		val drawn = list.first()
		list.remove(drawn)
		return drawn
	}

	private fun evalSubGame(p1: Int, pl1: MutableList<Int>, pl2: MutableList<Int>, order: List<Int>) {
		if (order[0] == p1) {
			pl1.addAll(order)
		} else {
			pl2.addAll(order)
		}
	}

	private fun canGoOn() = player1.size > 0 && player2.size > 0
	private fun canGoOn(state: Pair<List<Int>, List<Int>>) = canGoOn() && !deckHistory.contains(state)

	private fun copy(list: List<Int>): List<Int> = mutableListOf<Int>().apply { addAll(list) }

	private fun insert(v1: Int, v2: Int, list: MutableList<Int>) = list.apply { add(v1); add(v2) }
}