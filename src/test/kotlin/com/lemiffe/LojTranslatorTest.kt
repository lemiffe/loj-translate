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
    }

    // todo:
    //println(translator.translateToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
    //"Oh, no," she's saying, "our $400 blender can't handle something this hard!"
}
