package com.lemiffe

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val translator = LojTranslator()
        println(translator.translateWordToLoj("fleece"))
        println(translator.translateWordToLoj("fight"))
        println(translator.translateWordToLoj("triage"))
        println(translator.translateWordToLoj("the"))
        println(translator.translateWordToLoj("heist"))
    }
}
