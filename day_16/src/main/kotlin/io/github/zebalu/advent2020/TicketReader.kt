package io.github.zebalu.advent2020

object TicketReader {

	fun scanningRate(lines: List<List<String>>): Int {
		val ticket = Ticket(readFields(lines[0]))
		val nearbyTickets = lines[2].drop(1).map { it.split(",").map { it.toInt() } }
		return nearbyTickets.map { ticket.isValid(it) }.filter { !it.first }.map { it.second }.flatten().sum()
	}

	fun departure(lines: List<List<String>>): Long {
		val originalField = readFields(lines[0])
		val ticket = Ticket(originalField)
		val myTicket = readTicket(lines[1][1])
		val nearbyTickets = readNearbyTicket(lines[2].drop(1), ticket)
		val mapping = findMapping(originalField, nearbyTickets)
		return findDepartureIndices(originalField).map { myTicket[mapping[it]!!].toLong() }
			.fold(1L) { acc, next -> acc * next }
	}

	private fun findMapping(fields: List<ValidatableField>, tickets: List<List<Int>>): Map<Int, Int> {
		val mapping = mutableMapOf<Int, Int>()
		val remainingFields = fields.toMutableSet()
		while (!remainingFields.isEmpty()) {
			findDefinitlyMappedIndices(tickets[0].indices, remainingFields, tickets).map { i ->
				val field = findFieldThatAcceptsAllValues(remainingFields, tickets.map { it[i] })
				val idx = fields.indexOf(field)
				mapping[idx] = i
				remainingFields -= field
			}
		}
		return mapping
	}

	private fun findFieldThatAcceptsAllValues(fields: Set<ValidatableField>, values: List<Int>) =
		fields.find { vf -> values.all { v -> vf.accepts(v) } }!!

	private fun findDefinitlyMappedIndices(
		indices: IntRange,
		fields: Set<ValidatableField>,
		tickets: List<List<Int>>
	) = indices.map { i -> Pair(i, tickets.map { it[i] }) }
		.map { (i, values) -> Pair(isDefinitlyMapped(fields, values), i) }.filter { it.first }.map { it.second }

	private fun isDefinitlyMapped(fields: Set<ValidatableField>, values: List<Int>) =
		1 == fields.count { f -> values.all { v -> f.accepts(v) } }

	private fun readTicket(line: String): List<Int> = line.split(",").map { it.toInt() }

	private fun readNearbyTicket(lines: List<String>, ticket: Ticket): List<List<Int>> =
		lines.map { readTicket(it) }.filter { ticket.isValid(it).first }

	private fun findDepartureIndices(fields: List<ValidatableField>): List<Int> =
		fields.indices.filter { fields[it].getName().startsWith("departure") }

	private fun readFields(lines: List<String>): List<ValidatableField> {
		val regex = Regex("^(.+)(: )(\\d+)(-)(\\d+)( or )(\\d+)(-)(\\d+)$")
		return lines.map { line ->
			val (name, _, l1, _, u1, _, l2, _, u2) = regex.find(line)!!.destructured
			ValidatableField(name, l1.toInt()..u1.toInt(), l2.toInt()..u2.toInt())
		}
	}
}