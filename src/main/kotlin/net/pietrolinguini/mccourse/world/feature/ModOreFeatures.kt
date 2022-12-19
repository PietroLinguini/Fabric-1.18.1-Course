package net.pietrolinguini.mccourse.world.feature

import net.minecraft.world.gen.decorator.BiomePlacementModifier
import net.minecraft.world.gen.decorator.CountPlacementModifier
import net.minecraft.world.gen.decorator.PlacementModifier
import net.minecraft.world.gen.decorator.SquarePlacementModifier

object ModOreFeatures {
    fun modifiers(countModifier: PlacementModifier, heightModifier: PlacementModifier) =
        listOf(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())

    fun modifiersWithCount(count: Int, heightModifier: PlacementModifier) =
        modifiers(CountPlacementModifier.of(count), heightModifier)
}