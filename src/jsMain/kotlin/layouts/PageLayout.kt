package fr.xibalba.portfolio.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toAttrs
import fr.xibalba.portfolio.components.Footer
import fr.xibalba.portfolio.components.HEADER_HEIGHT
import fr.xibalba.portfolio.components.Header
import fr.xibalba.portfolio.components.setTitle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Main
import web.window.window

val MainTheme by ComponentStyle.base {
    Modifier
        .margin {
            top(HEADER_HEIGHT)
        }
        .width(100.percent)
        .minHeight(100.vh)
}

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    setTitle(title)

    Header(routes = setOf("/" to "Home", "/dummy" to "Dummy"))
    Main(MainTheme.toAttrs()) {
        content()
    }
    Footer()

    window.scroll(0.0, 0.0)
}