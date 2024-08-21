package fr.xibalba.politicalMeetings.scrap.utils

import fr.xibalba.politicalMeetings.scrap.Meeting
import fr.xibalba.politicalMeetings.scrap.Meetings

fun String.icsScrapMeetings(): Meetings {
    val eventStrings = Regex("BEGIN:VEVENT.+?END:VEVENT", RegexOption.DOT_MATCHES_ALL).findAll(this).map { it.value }
    return eventStrings.map { eventString ->
        val date = Regex("DTSTART;TZID=Europe/Paris:(\\d{8})").find(eventString)!!.groupValues[1].let {
            "${it.substring(6, 8)}/${it.substring(4, 6)}/${it.substring(0, 4)}"
        }
        val location = Regex("LOCATION:(.+)").find(eventString)!!.groupValues[1]
        Meeting(date, location)
    }.toList()
}