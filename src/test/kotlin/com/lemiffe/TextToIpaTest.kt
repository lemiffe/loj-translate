package com.lemiffe

import com.lemiffe.mappers.WordToIpa
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

    @Test fun testEnglishWordCanBeMappedToIpaWord() {
        val textToIpa = TextToIpa()
        textToIpa.loadDictionary(LojTranslator::class.java.getResource("/ipadict.txt").path)

        var mappedWord = WordToIpa.mapWordToIpa("mapped", textToIpa.lookup("mapped")[0])
        assertEquals(
            listOf(
                Pair("m", "m"),
                Pair("a", "æ"),
                Pair("p", "p"),
                Pair("p", "p"),
                Pair("e", null),
                Pair("d", "t")
            )
            , mappedWord
        )

        /*mappedWord = WordToIpa.mapWordToIpa("treetops", textToIpa.lookup("treetops")[0])
        assertEquals(
            listOf(
                Pair("t", "t"),
                Pair("r", "ɹ"),
                Pair("e", "i"),
                Pair("e", "i"),
                Pair("t", "t"),
                Pair("o", "ɔ"),
                Pair("p", "p"),
                Pair("s", "s")
            )
            , mappedWord
        )*/

        mappedWord = WordToIpa.mapWordToIpa("treichler", textToIpa.lookup("treichler")[0])
        // "tɹajˈkʌlɚ"

        mappedWord = WordToIpa.mapWordToIpa("trelleborg's", textToIpa.lookup("trelleborg's")[0])
        // "tɹɛˈlʌbɔɹgz"

        mappedWord = WordToIpa.mapWordToIpa("tremmel", textToIpa.lookup("tremmel")[0])
        // "tɹɛˈmʌl"

        mappedWord = WordToIpa.mapWordToIpa("troublemakers", textToIpa.lookup("troublemakers")[0])
        // "tɹʌˈbʌlmejˌkɚz"

        //aaberg			ɑˈbɚg
        //abbreviations		ʌbɹiˌviejˈʃʌnz
        //chico's		ʧiˈkowˌz
        //cinnamonson		sɪˈnʌmʌnsʌn
        //cipriano		ʧipɹiɑˈnow
        //circle			sɚˈkʌl
        //claes			klejˈz
        //clawed			klɔˈd
    }
}
