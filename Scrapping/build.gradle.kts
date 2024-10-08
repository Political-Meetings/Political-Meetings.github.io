plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

group = "fr.xibalba"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
}

kotlin {
    jvmToolchain(21)
}

task("run") {
    dependsOn("build")
    doLast {
        javaexec {
            mainClass = "fr.xibalba.politicalMeetings.scrap.MainKt"
            classpath = sourceSets["main"].runtimeClasspath
        }
    }
}