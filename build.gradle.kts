import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

val languages = listOf("en", "fr")

plugins {
    alias(libs.plugins.kotlin.multiplatform)

    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.kotlinx.serialization)
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
        browser {
            commonWebpackConfig {
                val isDev = project.findProperty("kobwebEnv") == "DEV"
                sourceMaps = isDev
                devServer?.open = false
            }
        }

        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
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