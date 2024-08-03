package fr.xibalba.politicalMeetings.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import fr.xibalba.politicalMeetings.components.Footer
import fr.xibalba.politicalMeetings.components.Header
import fr.xibalba.politicalMeetings.components.setTitle
import fr.xibalba.politicalMeetings.utils.marginTopAuto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Main
import web.window.window

val MainTheme = CssStyle.base {
    Modifier
        .width(100.percent)
        .minHeight(100.vh)
        .padding(leftRight = 60.px, top = 20.px)
}

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    setTitle(title)

    Column(MainTheme.toModifier()) {
        Header(routes = setOf("/" to "Home", "/dummy" to "Dummy", "/dummy1" to "Dummy1"))
        Main(Modifier.width(100.percent).padding(bottom = 100.px).toAttrs()) {
            content()
        }
        Box(Modifier.marginTopAuto()) {
            Footer()
        }
    }

    window.scroll(0.0, 0.0)
}