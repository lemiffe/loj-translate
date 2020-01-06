package com.lemiffe

import junit.framework.Assert.assertNull
import org.junit.Assert.*
import org.junit.Test

class TextToIpaTest {
    @Test fun testCanFindDictionary() {
        assertNull(LojTranslator::class.java.getResource("/wrong.txt"))
        assertNotNull(LojTranslator::class.java.getResource("/ipadict.txt"))
    }

    @Test fun testCantLoadDictionary() {
        val textToIpa = TextToIpa()
        var caughtException = false
        try {
            textToIpa.loadDictionary(null)
        } catch (exception: Exception) {
            // Note: Can be improved with AssertJ
            caughtException = true
        }
        assertTrue(caughtException)
    }

    @Test fun testTextCanBeConvertedToIpa() {
        val textToIpa = TextToIpa()
        textToIpa.loadDictionary(LojTranslator::class.java.getResource("/ipadict.txt").path)

        var ipaResults = textToIpa.lookup("house")
        assertEquals(1, ipaResults.size)

        ipaResults = textToIpa.lookup("where")
        assertEquals(2, ipaResults.size)

        ipaResults = textToIpa.lookup("iaofaoisjdoiajs")
        assertEquals(0, ipaResults.size)
    }
}
