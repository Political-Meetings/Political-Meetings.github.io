package fr.xibalba.politicalMeetings.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.silk.components.graphics.Image
import fr.xibalba.politicalMeetings.utils.Pair

@Composable
fun Logo(size: Pair<Int>) {
    Image("/assets/images/logo.svg", "Political Meetings", width = size.first, height = size.second)
}