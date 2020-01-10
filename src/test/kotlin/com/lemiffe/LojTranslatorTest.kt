package com.lemiffe

import junit.framework.Assert.assertNull
import org.junit.Assert.*
import org.junit.Test

class LojTranslatorTest {
    @Test fun testFullWordSubstitution() {
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
        assertEquals("b", result) // TODO: Revisit, add to dict? or apply rule?
    }

    // TODO: Implement full-sentence two-way translation tests
    /*
    println(translator.translateSentenceToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
    println(translator.translateSentenceToLoj("\"Oh, no,\" she's saying, \"our \$400 blender can't handle something this hard!\""))
    println(translator.translateSentenceToLoj("\"Hi John...\", she said. 'Oh, hi MATE, here's your $50!', he replied."))
    */
}
