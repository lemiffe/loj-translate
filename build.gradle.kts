import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.3.61"
    id("application")
    java
}

group = "com.lemiffe"
version = "0.0.1"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
    constraints  {
        compile("edu.stanford.nlp", "stanford-parser", "3.7.0")
    }

    // Logging
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

    // Logging Helper
    implementation("io.github.microutils:kotlin-logging:1.7.8")

}

application {
    mainClassName = "com.lemiffe.Main"
}
