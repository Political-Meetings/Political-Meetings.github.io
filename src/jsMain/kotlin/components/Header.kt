package fr.xibalba.portfolio.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.icons.mdi.MdiDarkMode
import com.varabyte.kobweb.silk.components.icons.mdi.MdiLightMode
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import fr.xibalba.portfolio.utils.centered
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text

val HEADER_HEIGHT = 75.px

val HeaderButtonStyle by ButtonStyle.addVariant {
    base {
        Modifier.size(HEADER_HEIGHT).background(Color.transparent).borderRadius(0.px)
    }
    val focusedModifier = Modifier.background(colorMode.toPalette().background.centered(0.15f))
    hover {
        focusedModifier
    }
    cssRule(".active") {
        focusedModifier
    }
}

@Composable
fun Header(routes: Set<Pair<String, String>>) {
    var theme by ColorMode.currentState
    val ctx = rememberPageContext()
    Row(Modifier
        .background(theme.toPalette().background.centered(0.05f))
        .position(Position.Sticky)
        .top(0.px).right(0.px).left(0.px)
        .width(100.percent).height(HEADER_HEIGHT),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            routes.forEach { (path, title) ->
                Button(onClick = { ctx.router.navigateTo(path) }, if (ctx.route.path == path) Modifier.classNames("active") else Modifier, HeaderButtonStyle) {
                    Text(title)
                }
            }
        }
        Row(Modifier.width(Width.FitContent).gap(10.px).margin(10.px),
            verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { theme = theme.opposite }, Modifier.width(30.px)) {
                if (theme.isLight) {
                    MdiDarkMode()
                } else {
                    MdiLightMode()
                }
            }
        }
    }
}