package net.pietrolinguini.mccourse
import net.fabricmc.api.ModInitializer
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.block.entity.ModBlockEntities
import net.pietrolinguini.mccourse.enchantments.ModEnchantments
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.painting.ModPaintings
import net.pietrolinguini.mccourse.recipe.ModRecipes
import net.pietrolinguini.mccourse.util.ModLootTableModifiers
import net.pietrolinguini.mccourse.util.ModRegistries
import net.pietrolinguini.mccourse.world.feature.ModConfiguredFeatures
import net.pietrolinguini.mccourse.world.gen.ModWorldGen

@Suppress("UNUSED")
object MCCourseMod: ModInitializer {
    const val MOD_ID = "mccourse"
    override fun onInitialize() {
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
    }
}