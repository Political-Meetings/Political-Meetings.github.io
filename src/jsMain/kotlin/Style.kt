package fr.xibalba.politicalMeetings

import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.theme.colors.palette.*

@InitSilk
fun initializeBreakpoints(ctx: InitSilkContext) {
    ctx.theme.breakpoints = BreakpointSizes(
        sm = 640.px,
        md = 768.px,
        lg = 1024.px,
        xl = 1280.px
    )
}

@InitSilk
fun overideTheme(ctx: InitSilkContext) {
    applyCustomPalette(
        CustomPalette(
            background = Color.rgb(0xFFFFFF),
            color = Color.rgb(0x000000),
            primary = Color.rgb(0x6200EE)
        ),
        ctx.theme.palettes.light
    )
    applyCustomPalette(
        CustomPalette(
            background = Color.rgb(0x121212),
            color = Color.rgb(0xFFFFFF),
            primary = Color.rgb(0x6200EE)
        ),
        ctx.theme.palettes.dark
    )
}

private fun applyCustomPalette(sourcePalette: CustomPalette, destinationPalette: MutablePalette) {
    destinationPalette.background = sourcePalette.background
    destinationPalette.color = sourcePalette.color
    destinationPalette.border = sourcePalette.color
    destinationPalette.focusOutline = sourcePalette.primary
    destinationPalette.overlay = sourcePalette.color
    destinationPalette.placeholder = sourcePalette.color
    destinationPalette.button.apply {
        default = sourcePalette.primary
        hover = sourcePalette.primary
        focus = sourcePalette.primary
        pressed = sourcePalette.primary
    }
    destinationPalette.link.apply {
        default = sourcePalette.primary
        visited = sourcePalette.primary
    }
    destinationPalette.checkbox.apply {
        background = sourcePalette.background
        hover = sourcePalette.primary
        color = sourcePalette.color
    }
    destinationPalette.input.apply {
        hoveredBorder = sourcePalette.primary
        invalidBorder = sourcePalette.primary
        filled = sourcePalette.background
        filledHover = sourcePalette.background
        filledFocus = sourcePalette.background
    }
    destinationPalette.switch.apply {
        backgroundOn = sourcePalette.background
        backgroundOff = sourcePalette.background
        thumb = sourcePalette.primary
    }
    destinationPalette.tab.apply {
        color = sourcePalette.color
        background = sourcePalette.background
        selectedColor = sourcePalette.primary
        selectedBackground = sourcePalette.background
        selectedBorder = sourcePalette.primary
        hover = sourcePalette.primary
        pressed = sourcePalette.primary
        disabled = sourcePalette.color
    }
    destinationPalette.tooltip.apply {
        background = sourcePalette.background
        color = sourcePalette.color
    }
}

private data class CustomPalette(val background: Color, val color: Color, val primary: Color)