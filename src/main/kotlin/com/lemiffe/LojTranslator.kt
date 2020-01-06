package com.lemiffe

import org.apache.logging.log4j.LogManager

/**
 * Rules:
 *
 * No letter “z”, use “s” instead
 * No letter “q”, use “k” instead
 * No letter “y”, use “j” instead (as in Dutch)
 * No letter “t”, use ` instead
 * No letter “h”, so any use in English would be dropped (silent) in
 * All double letters are dropped, so “pass the cheese” becomes “pás dé chës”
 * Whenever there is a t before a vocal, the vocal inherits it with a backtick accent, e.g. ò dö, but this is not necessary and the long form is preferred to a void loss of pronounciation data: `ö dö
 * Most words are based on English, prepositions and personal pronouns are based on Dutch, verbs ending in “ing” now end in “én”
 * Certain contractions like “can’t” and “won’t” use the Scottish form “cannae”, “willnae” (ní`)
 * The “ae” from Scots in “cannae/dunnae” becomes “í`” as in “cání`/dúnī`”
 * “The” becomes het (as in Dutch), but there is no “t” so it  becomes he`, but the abbreviation is ‘t in Dutch, so for out purposes the = "`”, e.g. “` bú`r”, but unlike Dutch there is no gender distinction between “het/de” and only “`” is used
 * All vocals are accented, either áéíóú for hard sounds, and äëïöü for the soft variants
 * -   The letter ç is a soft c, e.g. the “c” in Fleece, which would be written as Flëç, and is only used at the end of sentences that would usually have a vowel at the end in English
 * -   Words never end in a vocal
 * -   Words that end sounding “ees” like cheese, breeze, fleece, drop ending e, e.g. Chës, Brës, Flëç
 *
 * To implement:
 *
 * Diminutives: Objects can be made smaller by appending “ké” as in Flemish… e.g. “cálké” → Small caller. If an object ends in “k”, you must use “je” for the diminutive form… e.g. “book” → “bökjé” ← NOTE: This violates the rule of vowels at the end of words
 * Augmentatives: Objects can be made larger by appending “ón”
 * The “ár” digraph is used to indicate belonging/being from something, e.g. “English” → “Énglár” essentially replacing “ish”, e.g. “peckish” → “pécklár”
 * Past tense: Suffix with ` (e.g. fál → fál`)
 * Future tense: Suffix with `é` (e.g. fál → fál`é`)
 * Additional rules and direct word translations from ruleset
 *
 * Future improvements:
 *
 * The ^ (hat) symbol is used to string words that should be pronounced uninterruptedly together, as well as words that would be separated using hyphens in English (e.g. long-running process)
 * Primary punctuation consists of - and  — (which are used for pauses), and there is no need to end a paragraph with - or — unless you need to emphasise a long pause before starting the next paragraph, a full stop at the end of the text can be denoted with two vertical lines: ||
 */
class LojTranslator() {
    companion object {
        private val logger = LogManager.getLogger()
        private val textToIpa = TextToIpa()
    }

    init {
        val dictionaryPath = LojTranslator::class.java.getResource("/ipadict.txt").path
        logger.info("Loading dictionary from $dictionaryPath...")
        textToIpa.loadDictionary(dictionaryPath)
    }

    fun translateWordToLoj(word: String): String? {
        val ipaResults = textToIpa.lookup(word)
        var result = ""

        if (word.isEmpty()) {
            return ""
        }

        // If we have IPA data use that
        /*if (ipaResults.isNotEmpty()) {
            //TODO
        }*/

        // Translate based on standard rules
        if (word == "The") {
            return "`"
        }

        result = word
            .replace("z", "s")
            .replace("q", "k")
            .replace("y", "j")
            .replace("t", "`")
            .replace("h", "")
            .replace("aa", "a")
            .replace("bb", "b")
            .replace("cc", "c")
            .replace("dd", "d")
            .replace("ee", "e")
            .replace("ff", "f")
            .replace("gg", "g")
            .replace("ii", "i")
            .replace("jj", "j")
            .replace("kk", "k")
            .replace("ll", "l")
            .replace("mm", "m")
            .replace("nn", "n")
            .replace("oo", "o")
            .replace("pp", "p")
            .replace("rr", "r")
            .replace("ss", "s")
            .replace("uu", "u")
            .replace("vv", "v")
            .replace("ww", "w")
            .replace("xx", "x")

        if (result.endsWith("ing")) {
            result = result.replace("ing", "én")
        }

        if (result.endsWith("n't")) {
            result = result.replace("n't", "ní`")
        }

        // Todo: Conversion of "belonging like "english, chinese" must happen before vocal removal

        if (result.endsWithVocal()) {
            // Replace ending “ees” sounds like cheese, breeze, fleece (e.g. Chës, Brës, Flëç)
            result = result.replace("ese$","eç");
            result = result.replace("ece$","eç");
            result = result.replace("ace$","aç");
            result = result.replace("ase$","aç");
            result = result.replace("ice$","iç");
            result = result.replace("ise$","iç");
            result = result.replace("ose$","oç");
            result = result.replace("oce$","oç");
            result = result.replace("uce$","uç");
            result = result.replace("use$","uç");

            // If it still ends in vocal after replacements, drop the last character
            if (result.takeLast(1).endsWithVocal()) {
                result = result.dropLast(1)
            }
        }

        result = result
            .replace("a", "á")
            .replace("e", "é")
            .replace("i", "í")
            .replace("o", "ó")
            .replace("u", "ú")

        return result
    }
}

fun String.endsWithVocal (): Boolean {
    return (this.endsWith("a") or this.endsWith("e") or this.endsWith("i") or this.endsWith("o") or this.endsWith("u"))
}