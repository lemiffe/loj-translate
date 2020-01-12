package com.lemiffe.models.translator

enum class Capitalisation {
    CAPITALISED, UPPERCASE, LOWERCASE
}

enum class TranslatorAction {
    GLUE_RIGHT, GLUE_LEFT, REMOVE, KEEP
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
    OTHER
}

data class Word(
    var text: String,
    val capitalisation: Enum<Capitalisation>,
    var action: Enum<TranslatorAction>,
    val pos: Enum<POS>,
    var translation: String? = null
)
