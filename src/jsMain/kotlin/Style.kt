package fr.xibalba.portfolio

import com.varabyte.kobweb.silk.components.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.palette.background
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.theme.colors.palette.color

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
    ctx.theme.palettes.light.background = Color.rgb(0xF0F0F0)
    ctx.theme.palettes.light.color = Color.rgb(0x000000)

    ctx.theme.palettes.dark.background = Color.rgb(0x1A1A1A)
    ctx.theme.palettes.dark.color = Color.rgb(0xFFFFFF)
}

