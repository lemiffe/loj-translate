package com.lemiffe

import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import mu.KotlinLogging
import java.io.File

class LojTranslator() {
    companion object {
        private val logger = KotlinLogging.logger {}
        private val textToIpa = TextToIpa()
        private var parser: LexicalizedParser? = null
        private val lojDictionary: MutableMap<String, String> = mutableMapOf()
    }

    init {
        val dictionaryPath = LojTranslator::class.java.getResource("/ipadict.txt").path
        logger.info("Loading dictionary from $dictionaryPath...")
        textToIpa.loadDictionary(dictionaryPath)

        val grammarPath = LojTranslator::class.java.getResource("/englishPCFG.ser.gz").path
        logger.info("Loading model from $grammarPath...") // e.g. edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz
        parser = LexicalizedParser.loadModel(grammarPath)

        val lojDictionaryPath = LojTranslator::class.java.getResource("/english-to-loj-dict.txt").path
        File(lojDictionaryPath).forEachLine {
            val arr = it.split("\\s+".toRegex())
            if (arr.isNotEmpty() && arr.size > 1) {
                lojDictionary[arr[0]] = arr[1]
            }
        }
    }

    public fun translateSentenceToLoj(sentence: String): String {
        // "Hi John...", she said. 'Oh, hi MATE, here's your $50!', he replied.
        // [``/``, Hi/NNP, John/NNP, .../VBZ, ''/'', ,/,, she/PRP, said/VBD, ./., `/``, Oh/UH, ,/,, hi/UH, MATE/UH, ,/,, here/RB, 's/VBZ, your/PRP$, $/$, 50/CD, !/NN, '/'', ,/,, he/PRP, replied/VBD, ./.]
        // " jón...", séj sáíd. 'ó, vás má`é, éré's jïjn $50!', éj réplíéd.

        // Parse + Tag
        val taggedSentence = parser!!.parse(sentence).taggedYield()
        logger.info(taggedSentence.toString())

        // Tokenize
        val tokens = sentence.split("\\s+".toRegex())
        if (tokens.isEmpty()) {
            return ""
        }

        // Translate (lowercase)
        var translation = listOf<String?>()
        translation = tokens.map { translateWordToLoj(it.toLowerCase()) }.filter { it !== null && it.isNotEmpty() }

        // Re-capitalise

        // Put back together
        val result = translation.joinToString(separator = " ")

        // Correct

        return result

        // Todo:
        // The ^ (hat) symbol is used to string words that should be pronounced uninterruptedly together, as well as words that would be separated using hyphens in English (e.g. long-running process)
        // Primary punctuation consists of - and  — (which are used for pauses), and there is no need to end a paragraph with - or — unless you need to emphasise a long pause before starting the next paragraph, a full stop at the end of the text can be denoted with two vertical lines: ||
    }

    private fun translateWordToLoj(word: String): String? {
        val ipaResults = textToIpa.lookup(word)
        var result = ""

        if (word.isEmpty()) {
            return ""
        }

        // If we have IPA data use that
        /*if (ipaResults.isNotEmpty()) {
            //TODO
        }*/

        // Return known word from english-loj`ns dictionary
        if (lojDictionary.containsKey(word)) {
            return lojDictionary[word]
        }

        // Translate based on standard rules
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
            .replace("``", "`")
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
            result = result.replace("ese$".toRegex(),"eç")
                .replace("ece$".toRegex(),"eç")
                .replace("ace$".toRegex(),"aç")
                .replace("ase$".toRegex(),"aç")
                .replace("ice$".toRegex(),"iç")
                .replace("ise$".toRegex(),"iç")
                .replace("ose$".toRegex(),"oç")
                .replace("oce$".toRegex(),"oç")
                .replace("uce$".toRegex(),"uç")
                .replace("use$".toRegex(),"uç")

            // If it still ends in vocal after replacements, drop the last character
            if (result.endsWithVocal()) {
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

        // TODO: Diminutives: Objects can be made smaller by appending “ké`” as in Flemish… e.g. “cálké`” → Small caller. If an object ends in “k”, you must use “jé`” for the diminutive form… e.g. “book” → “bökjé`”
        // TODO: Augmentatives: Objects can be made larger by appending “ón”
        // TODO: The “ár” digraph is used to indicate belonging/being from something, e.g. “English” → “Énglár” essentially replacing “ish”, e.g. “peckish” → “pécklár”
        // TODO: Past tense: Suffix with ` (e.g. fál → fál`)
        // TODO: Future tense: Suffix with `é` (e.g. fál → fál`é`)
    }
}

fun String.endsWithVocal (): Boolean {
    return listOf("a", "e", "i", "o", "u").contains(this.takeLast(1).toLowerCase())
}


