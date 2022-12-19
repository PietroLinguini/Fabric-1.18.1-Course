package net.pietrolinguini.mccourse.world.feature

import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks

object ModConfiguredFeatures {
    val CHERRY_BLOSSOM_TREE =
        register("cherry_blossom", Feature.TREE.configure(
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LOG),
                StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                TwoLayersFeatureSize(1, 0, 2)
            ).build()
        ))

    private fun <FC: FeatureConfig> register(name: String, configuredFeature: ConfiguredFeature<FC, *>): ConfiguredFeature<FC, *> =
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Identifier(MCCourseMod.MOD_ID, name), configuredFeature)

    fun registerConfiguredFeatures() {
        println("Registering ModConfiguredFeatures for ${MCCourseMod.MOD_ID}")
    }
}