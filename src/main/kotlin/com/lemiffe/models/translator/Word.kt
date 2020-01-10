package com.lemiffe.models.translator

enum class Capitalisation {
    CAPITALISED, UPPERCASE, LOWERCASE, FIRST_LETTER_CAPITALISED
}

enum class TranslatorAction {
    GLUE_FORWARD, GLUE_BACKWARD, REMOVE, KEEP
}

enum class WordType {
    PUNCTUATION, SYMBOL, TEXT
}

enum class POS {
    CC,
    CD,
    DT,
    EX,
    FW,
    IN,
    JJ,
    JJR,
    JJS,
    LS,
    MD,
    NN,
    NNP,
    NNPS,
    NNS,
    PDT,
    POS,
    PRP,
    `PRP$`,
    RB,
    RBR,
    RBS,
    RP,
    SYM,
    TO,
    UH,
    VB,
    VBD,
    VBG,
    VBN,
    VBP,
    VBZ,
    WDT,
    WP,
    `WP$`,
    WRB,
}

data class Word(
    val text: String,
    val capitalisation: Enum<Capitalisation>,
    val action: Enum<TranslatorAction>,
    val type: Enum<WordType>,
    val pos: Enum<POS>
)