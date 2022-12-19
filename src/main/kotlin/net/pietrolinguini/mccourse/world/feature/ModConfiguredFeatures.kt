package net.pietrolinguini.mccourse.world.feature

import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks

object ModConfiguredFeatures {
    val CHERRY_BLOSSOM_TREE =
        register(
            "cherry_blossom", Feature.TREE.configure(
                TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LOG),
                    StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LEAVES),
                    BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    TwoLayersFeatureSize(1, 0, 2)
                ).build()
            )
        )

    val CHERRY_BLOSSOM_TREE_RANDOM =
        register(
            "cherry_blossom_feature",
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    listOf(
                        RandomFeatureEntry(
                            CHERRY_BLOSSOM_TREE.withWouldSurviveFilter(ModBlocks.CHERRY_BLOSSOM_SAPLING), 0.1f
                        )
                    ), CHERRY_BLOSSOM_TREE.withWouldSurviveFilter(ModBlocks.CHERRY_BLOSSOM_SAPLING)
                )
            )
        )

    val PINK_ROSE =
        register("pink_rose", Feature.FLOWER.configure(
            createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.PINK_ROSE), 64)
        ))

    private fun createRandomPatchFeatureConfig(block: BlockStateProvider, tries: Int): RandomPatchFeatureConfig =
        ConfiguredFeatures.createRandomPatchFeatureConfig(tries,
            Feature.SIMPLE_BLOCK.configure(SimpleBlockFeatureConfig(block)).withInAirFilter())

    private fun <FC : FeatureConfig> register(
        name: String,
        configuredFeature: ConfiguredFeature<FC, *>
    ): ConfiguredFeature<FC, *> =
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Identifier(MCCourseMod.MOD_ID, name), configuredFeature)

    fun registerConfiguredFeatures() {
        println("Registering ModConfiguredFeatures for ${MCCourseMod.MOD_ID}")
    }
}