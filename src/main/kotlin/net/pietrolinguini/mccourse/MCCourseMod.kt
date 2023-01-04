package net.pietrolinguini.mccourse
import net.fabricmc.api.ModInitializer
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.block.entity.ModBlockEntities
import net.pietrolinguini.mccourse.config.ModConfigs
import net.pietrolinguini.mccourse.effect.ModEffects
import net.pietrolinguini.mccourse.enchantments.ModEnchantments
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.painting.ModPaintings
import net.pietrolinguini.mccourse.potion.ModPotions
import net.pietrolinguini.mccourse.recipe.ModRecipes
import net.pietrolinguini.mccourse.util.ModLootTableModifiers
import net.pietrolinguini.mccourse.util.ModRegistries
import net.pietrolinguini.mccourse.world.feature.ModConfiguredFeatures
import net.pietrolinguini.mccourse.world.gen.ModWorldGen
import net.pietrolinguini.mccourse.world.structures.ModStructures
import org.apache.logging.log4j.LogManager

@Suppress("UNUSED")
object MCCourseMod: ModInitializer {
    const val MOD_ID = "mccourse"
    val LOGGER = LogManager.getLogger(MOD_ID)
    override fun onInitialize() {
        ModConfigs.registerConfigs()

        ModConfiguredFeatures.registerConfiguredFeatures()

        ModItems.registerModItems()
        ModBlocks.registerModBlocks()

        ModEnchantments.registerModEnchantments()

        ModRegistries.registerModStuffs()

        ModLootTableModifiers.modifyLootTables()
        ModPaintings.registerPaintings()

        ModRecipes.register()

        ModBlockEntities.registerAllEntities()

        ModWorldGen.generateModWorldGen()

        ModEffects.registerModEffects()
        ModPotions.registerPotions()

        ModStructures.registerSkyStructureFeatures()
    }
}