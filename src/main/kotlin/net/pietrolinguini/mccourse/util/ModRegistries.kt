package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.ComposterBlock
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.item.ModItems

object ModRegistries {
    fun registerModStuffs() {
        registerFuels()
        registerModComposterChances()
    }

    private fun registerFuels() {
        println("Registering Fuels for ${MCCourseMod.MOD_ID}")
        val registry = FuelRegistry.INSTANCE

        // 400 / 20 = 20 Seconds
        registry.add(ModItems.COAL_SLIVER, 400)
    }

    private fun registerModComposterChances() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP_SEEDS, 0.35f)
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP, 0.65f)
    }
}