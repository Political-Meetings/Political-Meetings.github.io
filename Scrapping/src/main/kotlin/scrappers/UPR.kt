package fr.xibalba.politicalMeetings.scrap.scrappers

import fr.xibalba.politicalMeetings.scrap.Scrapper
import fr.xibalba.politicalMeetings.scrap.output
import fr.xibalba.politicalMeetings.scrap.utils.icsScrapMeetings
import java.net.URI

object UPR : Scrapper {
    override fun scrap() {
        val url = "https://www.upr.fr/evenements/mois/?ical=1"
        val ics = URI(url).toURL().readText()
        output(ics.icsScrapMeetings(), "Union Populaire Républicaine")
    }
}