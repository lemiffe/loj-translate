package com.lemiffe.mappers

class WordToIpa {
    companion object {
        private val consonants: List<Char> = listOf(
            'b', 'd', 'ʧ', 'f', 'g', 'h', 'j', 'ʒ', 'k', 'l', 'm', 'n', 'ŋ', 'p',
            'r', 'ɹ', 's', 'ʃ', 't', 'θ', 'ð', 'v', 'w', 'z', 'ʤ'
        )

        private val vowels: List<Char> = listOf(
            'ʌ', 'a', 'ɑ', 'æ', 'e', 'ɛ', 'ɚ', 'i', 'ɪ', 'o', 'ɔ', 'ʊ', 'u'
        )

        private val neighbors: Map<Char, List<Char>> = mapOf(
            'a' to listOf('ʌ', 'ɑ', 'æ', 'e', 'ɛ', 'ɚ'), // ar+er=ɚ
            'c' to listOf('ʧ', 'k'),
            'd' to listOf('ʤ'), // dg
            'e' to listOf('ɚ'),
            'g' to listOf('ʒ', 'ʤ'),
            'j' to listOf('ʒ', 'ʤ'),
            'n' to listOf('ŋ'),
            'q' to listOf('k'),
            'r' to listOf('ɹ'),
            's' to listOf('z', 'ʃ', 'ʒ'),
            't' to listOf('θ', 'ð'),
            'x' to listOf('k', 'z'),
            'y' to listOf('j', 'ɪ'),
            'z' to listOf('s')
        )

        fun mapWordToIpa(word: String, ipaWord: String): List<Pair<Char, Char?>> {
            val result = mutableListOf<Pair<Char, Char?>>()
            for (char in word) {
                result.add(Pair(char, null))
            }

            for ((index, char) in word.withIndex()) {
                var currIndex = 0
                for ((ipaIndex, ipaChar) in ipaWord.withIndex()) {
                    if (ipaIndex > currIndex) {
                        if (char == ipaChar) {
                            // Map direct match
                            result[index] = Pair(char, ipaChar)
                            currIndex = ipaIndex
                            break
                        }
                    }
                }
            }

            // Map indirect consonants
            // Map vowels

            //mapped			mæˈpt
            //treetops		tɹiˈtɔˌps
            //treichler		tɹajˈkʌlɚ
            //trelleborg's		tɹɛˈlʌbɔɹgz
            //tremmel		tɹɛˈmʌl
            //troublemakers		tɹʌˈbʌlmejˌkɚz

            return result
        }
    }
}

fun Char.isEnglishConsonant (): Boolean {
    return !this.isEnglishVowel()
}

fun Char.isEnglishVowel (): Boolean {
    return listOf('a', 'e', 'i', 'o', 'u').contains(this.toLowerCase())
}