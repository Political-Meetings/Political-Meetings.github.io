package fr.xibalba.portfolio.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import fr.xibalba.portfolio.layouts.PageLayout
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Page("/dummy")
@Composable
fun Dummy() {
    PageLayout("Dummy") {
        Column(Modifier.fillMaxWidth().alignItems(AlignItems.Center)) {
            H1 {
                Text("Dummy page")
            }
        }
    }
}