package fr.xibalba.politicalMeetings.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fr.xibalba.politicalMeetings.utils.flatten
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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
        reloadParties()
    }
    return parties
}

suspend fun reloadParties() {
    val list = window.fetch("https://raw.githubusercontent.com/Political-Meetings/Political-Meetings.github.io/master/Api/parties.json").await()
    val partyNames: List<String> = Json.decodeFromString(list.text().await())
    val partiesPromises = partyNames.map { partyName ->
        window.fetch("https://raw.githubusercontent.com/Political-Meetings/Political-Meetings.github.io/master/Api/$partyName.party.json").then {
            it.text()
        }.flatten()
    }.toTypedArray()
    val partyJsons: List<String> = partiesPromises.map { it.await() }
    parties = partyJsons.map { partyJson ->
        Json.decodeFromString<Party>(partyJson)
    }
}