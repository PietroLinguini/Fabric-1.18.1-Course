package net.pietrolinguini.mccourse.event

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.TitleScreen
import net.pietrolinguini.mccourse.config.ModConfigs
import net.pietrolinguini.mccourse.util.KaupenTitleScreen

class ReplaceTitleScreenEvent: ScreenEvents.BeforeInit {
    override fun beforeInit(client: MinecraftClient?, screen: Screen?, scaledWidth: Int, scaledHeight: Int) {
        if (ModConfigs.REPLACE_TITLESCREEN && screen is TitleScreen && !(screen is KaupenTitleScreen))
            client?.setScreen(KaupenTitleScreen())
    }
}