package fr.xibalba.politicalMeetings

import com.varabyte.kobweb.compose.css.BackgroundColor
import com.varabyte.kobweb.compose.css.CSSColor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.LineHeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.theme.colors.palette.*
import fr.xibalba.politicalMeetings.utils.primary
import fr.xibalba.politicalMeetings.utils.secondary

val SecondaryButton = ButtonStyle.addVariantBase {
    Modifier.backgroundColor(colorMode.toPalette().secondary).color(colorMode.toPalette().color)
}

val UnstyledButton = ButtonStyle.addVariant {
    base {
        Modifier
            .color(CSSColor.Unset)
            .backgroundColor(BackgroundColor.Unset)
            .fontWeight(FontWeight.Normal)
            .lineHeight(LineHeight.Unset)
            .borderRadius(0.px)
    }
    hover {
        Modifier.backgroundColor(BackgroundColor.Unset)
    }
    active {
        Modifier.backgroundColor(BackgroundColor.Unset)
    }
}


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
fun setDefaultFont(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("body") {
        Modifier.fontFamily("Playfair Display", "Arial", "sans-serif")
    }
}

@InitSilk
fun overrideTheme(ctx: InitSilkContext) {
    applyCustomPalette(
        CustomPalette(
            background = Color.rgb(0xFFFFFF),
            color = Color.rgb(0x000000),
            primary = Color.rgb(0x5B4FA7),
            secondary = Color.rgb(0xADE6A4)
        ),
        ctx.theme.palettes.light
    )
    applyCustomPalette(
        CustomPalette(
            background = Color.rgb(0x000000),
            color = Color.rgb(0xF5F5F5),
            primary = Color.rgb(0x8A7FCE),
            secondary = Color.rgb(0x88C87E)
        ),
        ctx.theme.palettes.dark
    )
}

private fun applyCustomPalette(sourcePalette: CustomPalette, destinationPalette: MutablePalette) {
    destinationPalette.primary = sourcePalette.primary
    destinationPalette.secondary = sourcePalette.secondary
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

private data class CustomPalette(val background: Color, val color: Color, val primary: Color, val secondary: Color)