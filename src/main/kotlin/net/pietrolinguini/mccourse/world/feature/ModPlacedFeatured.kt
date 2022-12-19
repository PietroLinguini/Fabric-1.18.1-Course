package net.pietrolinguini.mccourse.world.feature

import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.pietrolinguini.mccourse.MCCourseMod

object ModPlacedFeatured {
    val CHERRY_BLOSSOM_PLACED_KEY = registerKey("cherry_blossom_placed")

    val CHERRY_BLOSSOM_PLACED = registerPlacedFeature(
        "cherry_blossom_placed",
        ModConfiguredFeatures.CHERRY_BLOSSOM_TREE_RANDOM.withPlacement(
            VegetationPlacedFeatures.modifiers(
                PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)
            )
        )
    )

    private fun registerPlacedFeature(name: String, placedFeature: PlacedFeature): PlacedFeature =
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Identifier(MCCourseMod.MOD_ID, name), placedFeature)

    private fun registerKey(name: String): RegistryKey<PlacedFeature> =
        RegistryKey.of(Registry.PLACED_FEATURE_KEY, Identifier(MCCourseMod.MOD_ID, name))
}