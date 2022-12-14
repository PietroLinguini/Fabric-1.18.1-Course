package net.pietrolinguini.mccourse.world.feature

import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier


object ModOreFeatures {
    fun modifiers(countModifier: PlacementModifier, heightModifier: PlacementModifier) =
        listOf(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())

    fun modifiersWithCount(count: Int, heightModifier: PlacementModifier) =
        modifiers(CountPlacementModifier.of(count), heightModifier)
}