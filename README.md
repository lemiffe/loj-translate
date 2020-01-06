# Loj Translate

English - Lój`ns rule-based translation software

## Local Development

Requirements:

* brew install kotlin <- Mac

Compiling:

* kotlinc src/main.kt -include-runtime -d app.jar
* java -jar app.jar

## Todo

* Project structure
* Logging
* EN->LN: Finish rule-based word translation
* EN->LN: IPA word implementation
* EN->LN: Translate phrases
* EN->LN: Add tests
* LN->EN: Implement full translation
* LN->EN: Add tests
* Gradle
* Spotless
* Gradlew?
* API blueprint
* Micronaut server
* Saga pattern
* Sentry
* Docker
* CI/CD
* K8s
* Indexing
* Search

## Resources

* Incorporated TextToIPA from https://github.com/surrsurus/text-to-ipa (JS -> Kotlin)
* Requires the CMU IPA dictionary (http://people.umass.edu/nconstan/CMU-IPA/)
