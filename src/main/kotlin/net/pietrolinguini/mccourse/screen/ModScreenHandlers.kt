package net.pietrolinguini.mccourse.screen

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod

object ModScreenHandlers {
    val ORICHALCUM_BLASTER_SCREEN_HANDLER =
        ScreenHandlerRegistry.registerSimple(Identifier(MCCourseMod.MOD_ID, "orichalcum_blaster"),
            ::OrichalcumBlasterScreenHandler)
}