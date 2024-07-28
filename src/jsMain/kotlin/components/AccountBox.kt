package fr.xibalba.politicalMeetings.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.pointerEvents
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.mdi.MdiAccountCircle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import fr.xibalba.politicalMeetings.UnstyledButton
import fr.xibalba.politicalMeetings.utils.primary
import org.jetbrains.compose.web.css.px

@Composable
fun AccountBox() {
    val theme by ColorMode.currentState
    Button(onClick = {

    }, variant = UnstyledButton) {
        MdiAccountCircle(Modifier.fontSize(120.px).color(theme.toPalette().primary).pointerEvents(PointerEvents.None))
    }
}