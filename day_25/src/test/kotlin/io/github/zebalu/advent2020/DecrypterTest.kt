package io.github.zebalu.advent2020

import org.junit.Test
import org.junit.Assert

class DecrypterTest {

	@Test
	fun example() {
	    Assert.assertEquals(14897079, Decrypter(7, 17807724, 5764801).getEncryptionKey())
	}
	
	@Test
	fun input1() {
	    Assert.assertEquals(19774660, Decrypter(7, 15628416, 11161639).getEncryptionKey())
	}

}