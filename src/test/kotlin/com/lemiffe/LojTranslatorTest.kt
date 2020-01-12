package com.lemiffe

import junit.framework.Assert.assertNull
import org.junit.Assert.*
import org.junit.Test

class LojTranslatorTest {
    @Test fun testFullWordSubstitutions() {
        val translator = LojTranslator()

        var result = translator.translateSentenceToLoj("the")
        assertEquals("`", result)

        result = translator.translateSentenceToLoj("man")
        assertEquals("vóé", result)

        result = translator.translateSentenceToLoj("wilnae")
        assertEquals("wíní`", result)

        result = translator.translateSentenceToLoj("")
        assertEquals("", result)

        result = translator.translateSentenceToLoj("bah")
        assertEquals("még", result)
    }

    @Test fun testTwoWordTranslations() {
        val translator = LojTranslator()

        var result = translator.translateSentenceToLoj("the cat")
        assertEquals("` cá`", result)

        result = translator.translateSentenceToLoj("No way")
        assertEquals("Ní` wáj", result) // TODO: Should be wäy after IPA introduction

        result = translator.translateSentenceToLoj("Oof WOW!")
        assertEquals("Óflá SÖMWÁM!", result)
    }

    // TODO: Implement full-sentence two-way translation tests
    /*
    println(translator.translateSentenceToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
    println(translator.translateSentenceToLoj("\"Oh, no,\" she's saying, \"our \$400 blender can't handle something this hard!\""))
    println(translator.translateSentenceToLoj("\"Hi John...\", she said. 'Oh, hi MATE, here's your $50!', he replied."))
    */
}
