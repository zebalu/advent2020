package io.github.zebalu.advent2020

class CardGame(lines: List<List<String>>) {
	val player1 = lines[0].drop(1).map { it.toInt() }.toMutableList()
	val player2 = lines[1].drop(1).map { it.toInt() }.toMutableList()

	fun calculateScore(): Int {
		while (canGoOn()) {
			playRound()
		}
		return calculateScoreOf(if (player1.size > player2.size) player1 else player2)
	}

	private fun calculateScoreOf(winner: List<Int>) =
		winner.mapIndexed { idx, value -> (winner.size - idx) * value }.sum()

	private fun playRound() {
		if (canGoOn()) {
			val p1 = player1.first()
			player1.remove(p1)
			val p2 = player2.first()
			player2.remove(p2)
			if (p1 > p2) {
				player1.add(p1)
				player1.add(p2)
			} else {
				player2.add(p2)
				player2.add(p1)
			}
		}
	}

	private fun canGoOn() = player1.size > 0 && player2.size > 0

}