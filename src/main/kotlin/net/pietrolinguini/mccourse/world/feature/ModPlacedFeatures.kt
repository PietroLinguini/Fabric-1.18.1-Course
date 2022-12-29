package net.pietrolinguini.mccourse.world.feature

import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier

object ModPlacedFeatures {
    val CHERRY_BLOSSOM_PLACED = PlacedFeatures.register("cherry_blossom_placed",
        ModConfiguredFeatures.CHERRY_BLOSSOM_SPAWN, VegetationPlacedFeatures.modifiers(
            PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)))

    val PINK_ROSE_PLACED = PlacedFeatures.register("pink_rose_placed",
        ModConfiguredFeatures.PINK_ROSE, RarityFilterPlacementModifier.of(4),
            SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of())

    val ORICHALCUM_ORE_PLACED = PlacedFeatures.register("orichalcum_ore_placed",
        ModConfiguredFeatures.ORICHALCUM_ORE, ModOreFeatures.modifiersWithCount(7,
            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))))

}