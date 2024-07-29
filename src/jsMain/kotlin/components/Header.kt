package fr.xibalba.politicalMeetings.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import fr.xibalba.politicalMeetings.SecondaryButton
import fr.xibalba.politicalMeetings.utils.unaryPlus
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1

@Composable
fun Header(routes: Set<Pair<String, String>>) {
    var theme by ColorMode.currentState
    val ctx = rememberPageContext()
    Row(Modifier.position(Position.Sticky).width(100.percent), verticalAlignment = Alignment.CenterVertically) {
        Link("/") {
            Logo(244 to 227)
        }
        Column(Modifier.width(100.percent)) {
            H1(Modifier.fontSize(80.px).margin(topBottom = 10.px).toAttrs()) {
                +"Political Meetings"
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.width(100.percent)) {
                for ((route, name) in routes) {
                    Button(onClick = { ctx.router.navigateTo(route) }, variant = SecondaryButton, modifier = Modifier.width((100 / routes.size).percent)) {
                        +name
                    }
                }
            }
        }
        AccountBox()
    }
}