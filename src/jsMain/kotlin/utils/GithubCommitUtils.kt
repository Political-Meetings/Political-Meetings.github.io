package fr.xibalba.politicalMeetings.utils

import fr.xibalba.politicalMeetings.AppHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
data class GithubCommit(
    val message: String,
    val sha: String,
    val content: String,
)

@Serializable
private data class GithubResponse(
    val sha: String
)

suspend fun getSha(token: String, path: String): String {
    val response = AppHttpClient.get {
        url("https://api.github.com/repos/Political-Meetings/Political-Meetings.github.io/contents/$path")
        headers {
            append("Authorization", "token $token")
            append("Accept", "application/vnd.github+json")
            append("X-GitHub-Api-Version", "2022-11-28")
        }
    }
    return response.body<GithubResponse>().sha
}

@OptIn(ExperimentalEncodingApi::class)
suspend inline fun <reified T> editFile(token: String, path: String, content: T) =
    AppHttpClient.put {
        url("https://api.github.com/repos/Political-Meetings/Political-Meetings.github.io/contents/$path")
        headers {
            append("Authorization", "token $token")
            append("Accept", "application/vnd.github+json")
            append("X-GitHub-Api-Version", "2022-11-28")
        }
        val sha = getSha(token, path)
        setBody(GithubCommit("Update parties.json", sha, Base64.encode(Json.encodeToString(content).encodeToByteArray())))
        contentType(ContentType.Application.Json)
    }