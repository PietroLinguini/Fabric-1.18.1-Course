package net.pietrolinguini.mccourse.world.gen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep
import net.pietrolinguini.mccourse.world.feature.ModPlacedFeatures

object ModOreGeneration {
    fun generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORICHALCUM_ORE_PLACED.key.get())
    }
}