package fr.xibalba.portfolio.utils

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.DEFAULT_SHIFTING_PERCENT
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.graphics.luminance

fun Color.centered(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.darkened(byPercent) else this.lightened(byPercent)

fun Color.extremized(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.lightened(byPercent) else this.darkened(byPercent)