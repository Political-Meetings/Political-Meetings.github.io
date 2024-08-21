package fr.xibalba.politicalMeetings.scrap

import fr.xibalba.politicalMeetings.scrap.scrappers.UPR
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    UPR.scrap()
}

interface Scrapper {
    fun scrap()
}

@Suppress("UnusedReceiverParameter")
fun Scrapper.output(data: Meetings, partyName: String) {
    val file = File("output/$partyName.party.json")
    file.parentFile.mkdirs()
    file.writeText(Json.encodeToString<Party>(Party(partyName, data)))
}

typealias Meetings = List<Meeting>

@Serializable
data class Meeting(
    val date: String,
    val location: String,
)

@Serializable
data class Party(
    val name: String,
    val meetings: List<Meeting>,
)