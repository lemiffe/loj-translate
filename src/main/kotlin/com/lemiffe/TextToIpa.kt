package com.lemiffe

import mu.KotlinLogging
import java.io.File

/**
 * English Text to IPA converter
 * Based on https://github.com/surrsurus/text-to-ipa/blob/master/lib/text-to-ipa.js by surrsurus
 */
class TextToIpa {
    companion object {
        private val logger = KotlinLogging.logger {}
        private var dictionary: MutableMap<String, String> = mutableMapOf();
    }

    fun loadDictionary (fileName: String?) {
        if (fileName == null || !File(fileName).exists()) {
            logger.error("Could not load dictionary, '$fileName' does not exist")
            throw Exception("File '$fileName' does not exist")
        }

        File(fileName).forEachLine {
            val arr = it.split("\\s+".toRegex())
            if (arr.isNotEmpty() && arr.size > 1) {
                dictionary[arr[0]] = arr[1]
            }
        }
    }

    fun lookup (word: String): List<String> {
        if (dictionary.isEmpty()) {
            throw Exception("No data in IPA dictionary, was it loaded?");
        }

        if (!dictionary.containsKey(word)) {
            return listOf()
        }

        // Retrieve IPA text (can have multiple pronunciations)
        val results: MutableList<String> = mutableListOf(dictionary[word]!!)
        for (i in 1..3) {
            if (dictionary.containsKey("$word($i)")) {
                results.add(dictionary["$word($i)"]!!)
            }
        }

        return results
    }
}