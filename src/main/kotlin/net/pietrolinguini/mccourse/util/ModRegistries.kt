package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.item.ModItems

object ModRegistries {
    fun registerModStuffs() {
        registerFuels()
    }

    private fun registerFuels() {
        println("Registering Fuels for ${MCCourseMod.MOD_ID}")
        val registry = FuelRegistry.INSTANCE

        // 400 / 20 = 20 Seconds
        registry.add(ModItems.COAL_SLIVER, 400)
    }
}