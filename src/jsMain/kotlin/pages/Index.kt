package fr.xibalba.politicalMeetings.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import fr.xibalba.politicalMeetings.components.MeetingCard
import fr.xibalba.politicalMeetings.data.Party
import fr.xibalba.politicalMeetings.data.getParties
import fr.xibalba.politicalMeetings.layouts.PageLayout
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Page("/index")
@Composable
fun Index() {
    var parties by remember { mutableStateOf(emptyList<Party>()) }
    PageLayout("Home") {
        Column(Modifier.fillMaxWidth().alignItems(AlignItems.Center)) {
            H1 {
                Text("Home")
            }
            for ((party, meeting) in parties.flatMap { it.meetings.map { meeting -> it to meeting } }) {
                MeetingCard(party, meeting)
            }
        }
    }
    LaunchedEffect(Unit) {
        parties = getParties()
        println("Parties loaded!")
    }
}