# Loj Translate

English - Lój`ns rule-based translation software

## Local Development

Requirements:

* brew install kotlin
* brew install gradle (recommended but not necessary)

Run:

* ./gradlew build
* ./gradlew run
* ./gradlew test

## Task List

Translator tasks:

* EN->LN: Finish rule-based word translation
* EN->LN: IPA word implementation
* EN->LN: Translate phrases
* EN->LN: Add tests
* LN->EN: Implement full translation
* LN->EN: Add tests
* Spotless
* Library: https://guides.gradle.org/building-kotlin-jvm-libraries/
* Release

Server tasks:

* API blueprint
* Micronaut server
* Coroutines
* Saga pattern
* Sentry
* Docker
* CI/CD
* K8s/Heroku
* Indexing
* Search
* Release: https://github.com/heroku/kotlin-getting-started

## Resources & References

* Translated TextToIPA from JS to Kotlin: https://github.com/surrsurus/text-to-ipa
* Incorporates the CMU IPA dictionary: http://people.umass.edu/nconstan/CMU-IPA/
* Uses the Stanford Parser: https://nlp.stanford.edu/software/lex-parser.html
* POS basics: https://www.englishclub.com/grammar/parts-of-speech.htm
* Stanford Parser POS abbreviations: https://www.computing.dcu.ie/~acahill/tagset.html (broken link)
    * Archive: https://web.archive.org/web/20140313010853/http://www.computing.dcu.ie/~acahill/tagset.html
