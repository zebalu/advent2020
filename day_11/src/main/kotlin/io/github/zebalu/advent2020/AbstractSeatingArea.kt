package io.github.zebalu.advent2020

abstract class AbstractSeatingArea(lines:List<String>, private val toleration: Int) {
    protected val area: Array<CharArray> = Array(lines.size) { i -> lines[i].toCharArray() }

    fun countStableState(): Int {
        repeatUntilStabilize()
        return area.flatMap { sa -> sa.asSequence() }.count { c -> c == '#' }
    }

    private fun repeatUntilStabilize() {
        while (applyNextStep());
    }

    private fun applyNextStep(): Boolean {
        val (next, changed) = calcNext()
        if (changed) {
            next.copyInto(area)
        }
        return changed
    }
	
	fun allStepsTillStable():List<Array<CharArray>> {
		val result = mutableListOf<Array<CharArray>>()
		result += copyOfArea()
		while(applyNextStep()) {
			result += copyOfArea()
		}
		return result
	}
	
	private fun copyOfArea():Array<CharArray> = Array(area.size) {i -> area[i].copyOf() }
	

    private fun calcNext(): Pair<Array<CharArray>, Boolean> {
        val copy = copyOfArea()
        var changed = false
        for (i in copy.indices) {
            for (j in copy[i].indices) {
                val future = future(i, j)
                changed = changed || future != copy[i][j]
                copy[i][j] = future
            }
        }
        return Pair(copy, changed)
    }

    private fun future(i: Int, j: Int): Char = if (shouldLeave(i, j)) 'L' else if (shouldSit(i, j)) '#' else area[i][j]

    private fun shouldLeave(i: Int, j: Int): Boolean = area[i][j] == '#' && countAdjacentOccupiedSeats(i, j) >= toleration

    private fun shouldSit(i: Int, j: Int): Boolean = area[i][j] == 'L' && countAdjacentOccupiedSeats(i, j) == 0

    private fun countAdjacentOccupiedSeats(i: Int, j: Int) = collectAdjacentSeats(i, j).count { c -> c == '#' }

    protected abstract fun collectAdjacentSeats(i: Int, j: Int): List<Char>;
}