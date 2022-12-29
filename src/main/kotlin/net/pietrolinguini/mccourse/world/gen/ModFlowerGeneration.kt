package net.pietrolinguini.mccourse.world.gen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.pietrolinguini.mccourse.world.feature.ModPlacedFeatures

object ModFlowerGeneration {
    fun generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PINK_ROSE_PLACED.key.get())
    }
}