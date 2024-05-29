package fr.xibalba.politicalMeetings.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import fr.xibalba.politicalMeetings.data.Party
import fr.xibalba.politicalMeetings.data.getParties
import fr.xibalba.politicalMeetings.layouts.PageLayout
import fr.xibalba.politicalMeetings.utils.H2
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
            for (party in parties) {
                H2(party.name)
                for (meeting in party.meetings) {
                    Text("${meeting.date} - ${meeting.location}")
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        parties = getParties()
        println("Parties loaded!")
    }
}