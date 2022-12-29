package net.pietrolinguini.mccourse.world.feature

import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks

object ModConfiguredFeatures {
    val CHERRY_BLOSSOM_TREE: RegistryEntry<ConfiguredFeature<TreeFeatureConfig, *>> =
        ConfiguredFeatures.register(
            "cherry_blossom", Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LOG),
                StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.of(ModBlocks.CHERRY_BLOSSOM_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                TwoLayersFeatureSize(1, 0, 2)
            ).build()
        )

    val CHERRY_BLOSSOM_CHECKED = PlacedFeatures.register("cherry_blossom_checked",
        CHERRY_BLOSSOM_TREE, PlacedFeatures.wouldSurvive(ModBlocks.CHERRY_BLOSSOM_SAPLING))

    val CHERRY_BLOSSOM_SPAWN = ConfiguredFeatures.register("cherry_blossom_spawn",
        Feature.RANDOM_SELECTOR, RandomFeatureConfig(listOf(RandomFeatureEntry(CHERRY_BLOSSOM_CHECKED,
            0.5f)), CHERRY_BLOSSOM_CHECKED))

    val PINK_ROSE =
        ConfiguredFeatures.register("flower_pink_rose", Feature.FLOWER,
            RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PINK_ROSE))
            ))
        )

    val OVERWORLD_ORICHALCUM_ORES = listOf(
        OreFeatureConfig.createTarget(
            OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORICHALCUM_ORE.defaultState
        ),
        OreFeatureConfig.createTarget(
            OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORICHALCUM_ORE.defaultState
        )
    )

    val ORICHALCUM_ORE = ConfiguredFeatures.register("orichalcum_ore",
        Feature.ORE, OreFeatureConfig(OVERWORLD_ORICHALCUM_ORES, 9))

    fun registerConfiguredFeatures() {
        println("Registering ModConfiguredFeatures for ${MCCourseMod.MOD_ID}")
    }
}