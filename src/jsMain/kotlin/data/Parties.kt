package fr.xibalba.politicalMeetings.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fr.xibalba.politicalMeetings.AppCoroutineScope
import fr.xibalba.politicalMeetings.AppHttpClient
import fr.xibalba.politicalMeetings.utils.bodyTextAsJson
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.serialization.Serializable

typealias Parties = List<Party>

@Serializable
data class Party(
    val name: String,
    val meetings: List<Meeting>,
)

@Serializable
data class Meeting(
    val date: String,
    val location: String,
)

private var parties by mutableStateOf<Parties>(emptyList())

suspend fun getParties(): Parties {
    if (parties.isEmpty()) {
        val list = AppHttpClient.get {
            url("https://raw.githubusercontent.com/Political-Meetings/Political-Meetings.github.io/master/Api/parties.json")
            headers {
                append("Accept", "application/json")
            }
        }.bodyTextAsJson<List<String>>()
        parties = list.map { partyName ->
            AppCoroutineScope.async {
                AppHttpClient.get {
                    url("https://raw.githubusercontent.com/Political-Meetings/Political-Meetings.github.io/master/Api/$partyName.party.json")
                }.bodyTextAsJson<Party>()
            }
        }.awaitAll()
    }
    return parties
}

fun setParties(newParties: Parties) {
    parties = newParties
}