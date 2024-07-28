package fr.xibalba.politicalMeetings.utils

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.DEFAULT_SHIFTING_PERCENT
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.graphics.luminance
import com.varabyte.kobweb.silk.theme.colors.palette.MutablePalette
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import org.jetbrains.compose.web.dom.Text

fun Color.centered(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.darkened(byPercent) else this.lightened(byPercent)

fun Color.extremized(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.lightened(byPercent) else this.darkened(byPercent)

@Composable
operator fun String.unaryPlus() = Text(this)

val Palette.primary get() = this.getValue("primary")
var MutablePalette.primary
    get() = this.getValue("primary")
    set(value) { this["primary"] = value }

val Palette.secondary get() = this.getValue("secondary")
var MutablePalette.secondary
    get() = this.getValue("secondary")
    set(value) { this["secondary"] = value }
