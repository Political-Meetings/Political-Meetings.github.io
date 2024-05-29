package fr.xibalba.politicalMeetings.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

@Composable
fun H2(text: String) {
    org.jetbrains.compose.web.dom.H2 {
        Text(text)
    }
}