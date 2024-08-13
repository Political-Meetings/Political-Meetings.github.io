import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

val languages = listOf("en", "fr")

plugins {
    alias(libs.plugins.kotlin.multiplatform)

    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
}

group = "fr.xibalba.politicalMeetings"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

kobweb {
    app {
        export {
            includeSourceMap = false
        }
    }
}

kotlin {
    configAsKobwebApplication("app")

    js(IR) {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions.target = "es2015"
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(libs.compose.html.core)
                implementation(libs.compose.runtime)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.kobwebx.markdown)
                implementation(libs.silk.icons.mdi)
                implementation(libs.silk.icons.fa)
                implementation(libs.kotlinx.wrappers.browser)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.engine)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.serialization.json)

                implementation(npm("marked", project.extra["npm.marked.version"].toString()))
            }
        }
    }
}