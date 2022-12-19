package net.pietrolinguini.mccourse.world.feature

import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.decorator.BiomePlacementModifier
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier
import net.minecraft.world.gen.decorator.SquarePlacementModifier
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.pietrolinguini.mccourse.MCCourseMod

object ModPlacedFeatures {
    val CHERRY_BLOSSOM_PLACED_KEY = registerKey("cherry_blossom_placed")
    val PINK_ROSE_PLACED_KEY = registerKey("pink_rose_placed")

    val CHERRY_BLOSSOM_PLACED = registerPlacedFeature(
        "cherry_blossom_placed",
        ModConfiguredFeatures.CHERRY_BLOSSOM_TREE_RANDOM.withPlacement(
            VegetationPlacedFeatures.modifiers(
                PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)
            )
        )
    )

    val PINK_ROSE_PLACED = registerPlacedFeature(
        "pink_rose_placed",
        ModConfiguredFeatures.PINK_ROSE.withPlacement(
            RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()
        )
    )

    private fun registerPlacedFeature(name: String, placedFeature: PlacedFeature): PlacedFeature =
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Identifier(MCCourseMod.MOD_ID, name), placedFeature)

    private fun registerKey(name: String): RegistryKey<PlacedFeature> =
        RegistryKey.of(Registry.PLACED_FEATURE_KEY, Identifier(MCCourseMod.MOD_ID, name))
}