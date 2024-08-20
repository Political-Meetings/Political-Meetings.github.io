package fr.xibalba.politicalMeetings.utils

import fr.xibalba.politicalMeetings.AppJson
import io.ktor.client.statement.*

inline fun <reified T> String.json() = AppJson.decodeFromString<T>(this)

suspend inline fun <reified T> HttpResponse.bodyTextAsJson() = this.bodyAsText().json<T>()