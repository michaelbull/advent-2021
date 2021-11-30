group = "com.michael-bull.advent"
version = "1.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.github.michaelbull.advent2021.MainKt")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
