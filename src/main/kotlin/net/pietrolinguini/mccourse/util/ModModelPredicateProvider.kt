package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.item.ModItems

object ModModelPredicateProvider {
    fun registerModModels() {
        FabricModelPredicateProviderRegistry.register(
            ModItems.DATA_TABLET,
            Identifier(MCCourseMod.MOD_ID, "on")
        ) { stack, _, _, _ ->
            if (stack.hasNbt()) 1f else 0f
        }
    }
}