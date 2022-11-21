package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.item.Item
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

        FabricModelPredicateProviderRegistry.register(
            ModItems.BLOCK_GRABBER,
            Identifier(MCCourseMod.MOD_ID, "on")
        ) { stack, _, _, _ ->
            if (stack.hasNbt()) 1f else 0f
        }

        registerBow(ModItems.ORICHALCUM_BOW)
    }

    private fun registerBow(bow: Item) {
        FabricModelPredicateProviderRegistry.register(bow, Identifier("pull")) { stack, _, entity, _ ->
            if (entity == null) {
                0f
            } else if (entity.activeItem != stack) {
                0f
            } else
                (stack.maxUseTime - entity.itemUseTimeLeft) / 20f
        }

        FabricModelPredicateProviderRegistry.register(bow, Identifier("pulling")) { stack, _, entity, _ ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) {
                1f
            } else
                0f
        }
    }
}