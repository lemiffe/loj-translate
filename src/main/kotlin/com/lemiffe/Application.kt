package com.lemiffe

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val translator = LojTranslator()
        println(translator.translateSentenceToLoj("fleece"))
        println(translator.translateSentenceToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
        //"Oh, no," she's saying, "our $400 blender can't handle something this hard!"
    }
}
