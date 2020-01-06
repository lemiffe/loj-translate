package com.lemiffe

import java.io.File

/**
 * English Text to IPA converter
 * Based on https://github.com/surrsurus/text-to-ipa/blob/master/lib/text-to-ipa.js by surrsurus
 */
class TextToIpa {
    private var dictionary: MutableMap<String, String> = mutableMapOf();

    fun loadDictionary (fileName: String) {
        File(fileName).forEachLine {
            val arr = it.split("\\s+".toRegex())
            if (arr.isNotEmpty() && arr.size > 1) {
                this.dictionary[arr[0]] = arr[1]
            }
        }
    }

    fun lookup (word: String): List<String> {
        if (this.dictionary.isEmpty()) {
            throw Exception("No data in IPA dictionary, was it loaded?");
        }

        if (!this.dictionary.containsKey(word)) {
            return listOf()
        }

        // Retrieve IPA text (can have multiple pronunciations)
        val results: MutableList<String> = mutableListOf(this.dictionary[word]!!)
        for (i in 1..3) {
            if (dictionary.containsKey("$word($i)")) {
                results.add(this.dictionary["$word($i)"]!!)
            }
        }

        return results
    }
}