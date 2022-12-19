package net.pietrolinguini.mccourse.world.feature.tree

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.pietrolinguini.mccourse.world.feature.ModConfiguredFeatures
import java.util.*

class CherryBlossomSaplingGenerator: SaplingGenerator() {
    override fun getTreeFeature(random: Random?, bees: Boolean): ConfiguredFeature<*, *> = ModConfiguredFeatures.CHERRY_BLOSSOM_TREE
}