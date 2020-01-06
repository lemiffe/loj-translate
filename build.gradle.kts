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
        compile("org.apache.logging.log4j", "log4j-core", "2.13.0")
    }
}

application {
    mainClassName = "com.lemiffe.Main"
}
