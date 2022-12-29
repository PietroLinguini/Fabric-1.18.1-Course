package net.pietrolinguini.mccourse.world.feature.tree

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.pietrolinguini.mccourse.world.feature.ModConfiguredFeatures
import java.util.*

class CherryBlossomSaplingGenerator: SaplingGenerator() {
    override fun getTreeFeature(random: Random?, bees: Boolean): RegistryEntry<out ConfiguredFeature<*, *>> =
        ModConfiguredFeatures.CHERRY_BLOSSOM_TREE
}