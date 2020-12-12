package io.github.zebalu.advent2020

class AdvancedSeatingArea(lines: List<String>) : AbstractSeatingArea(lines, 5) {

    override fun collectAdjacentSeats(i: Int, j: Int): List<Char> {
        val result = mutableListOf<Char>()
        for (iDiff in -1..1) {
            for (jDiff in -1..1) {
                if (!(iDiff == jDiff && iDiff == 0) && findFirstNotFlore(i, j, iDiff, jDiff) == '#' ) {
                    result.add('#')
                }
            }
        }
        return result
    }

    private fun findFirstNotFlore(i: Int, j: Int, iDiff: Int, jDiff: Int): Char {
        var ii = i + iDiff
        var jj = j + jDiff
        while (ii >= 0 && jj >= 0 && ii < area.size && jj < area[ii].size && area[ii][jj] == '.') {
            ii += iDiff
            jj += jDiff
        }
        if (ii >= 0 && jj >= 0 && ii < area.size && jj < area[ii].size) {
            return area[ii][jj]
        } else {
            return '.'
        }
    }
}