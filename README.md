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
    * Whole-word substitution with switch
* EN->LN: IPA word implementation
* EN->LN: Translate phrases
* EN->LN: Add tests
* LN->EN: Implement full translation
* LN->EN: Add tests
* Spotless

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

## Resources

* Incorporated TextToIPA from https://github.com/surrsurus/text-to-ipa (JS -> Kotlin)
* Requires the CMU IPA dictionary (http://people.umass.edu/nconstan/CMU-IPA/)
