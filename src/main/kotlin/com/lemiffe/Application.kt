package com.lemiffe

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val translator = LojTranslator()
        //println(translator.translateSentenceToLoj("fleece"))
        //println(translator.translateSentenceToLoj("My tiny little dog also likes chewing big table legs, which I think is annoying"))
        //println(translator.translateSentenceToLoj("\"Oh, no,\" she's saying, \"our \$400 blender can't handle something this hard!\""))
        println(translator.translateSentenceToLoj("\"Hi John...\", she said. 'Oh, hi MATE, here's your $50!', he replied."))
    }
}
