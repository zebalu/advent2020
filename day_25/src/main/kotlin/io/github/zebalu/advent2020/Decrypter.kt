package io.github.zebalu.advent2020

class Decrypter(val subjectNumber: Int, val doorPublicKey: Int, val cardPublicKey: Int) {
	private val dividor = 20201227
	private val doorLoopSize = findLoopSize(doorPublicKey)
	private val cardLoopSize = findLoopSize(cardPublicKey)

	private fun findLoopSize(publicKey: Int): Int {
		var count = 0
		var remainder = 1
		while (remainder != publicKey) {
			remainder = (remainder * subjectNumber) % dividor
			++count
		}
		return count
	}

	fun getEncryptionKey(): Long {
		return getEncryptionKey(cardPublicKey, doorLoopSize)
	}

	private fun getEncryptionKey(sNum: Int, lSize: Int): Long {
		var result = 1L
		repeat(lSize) {
			result = (result * sNum) % dividor
		}
		return result
	}
}