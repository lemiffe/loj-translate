package com.lemiffe

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val translator = LojTranslator()
        println(translator.translateWordToLoj("fleece"))
        println(translator.translateToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
        //"Oh, no," she's saying, "our $400 blender can't handle something this hard!"
    }
}
