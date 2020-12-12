package io.github.zebalu.advent2020

class SimpleSeatingArea(lines: List<String>): AbstractSeatingArea(lines, 4) {

    override fun collectAdjacentSeats(i: Int, j: Int): List<Char> {
        val result = mutableListOf<Char>()
        for (ii in i - 1..i + 1) {
            for (jj in j - 1..j + 1) {
                if ((ii != i || jj != j) && (ii > -1 && jj > -1 && ii < area.size && jj < area[ii].size)) {
                    result.add(area[ii][jj])
                }
            }
        }
        return result
    }
}