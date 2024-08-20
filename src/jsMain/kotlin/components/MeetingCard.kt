package fr.xibalba.politicalMeetings.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.icons.mdi.MdiCalendarMonth
import com.varabyte.kobweb.silk.components.icons.mdi.MdiLocationPin
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import fr.xibalba.politicalMeetings.data.Meeting
import fr.xibalba.politicalMeetings.data.Party
import fr.xibalba.politicalMeetings.utils.centered
import fr.xibalba.politicalMeetings.utils.primary
import fr.xibalba.politicalMeetings.utils.unaryPlus
import org.jetbrains.compose.web.css.px

@Composable
fun MeetingCard(party: Party, meeting: Meeting) {
    val theme by ColorMode.currentState
    Column(Modifier.padding(16.px).borderRadius(8.px).background(theme.toPalette().primary.centered(0.3F))) {
        Row {
            //image
            +party.name
        }
        Row {
            MdiLocationPin()
            +meeting.location
        }
        Row {
            MdiCalendarMonth()
            +meeting.date
        }
    }
}