package net.pietrolinguini.mccourse
import net.fabricmc.api.ModInitializer
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.util.ModRegistries

@Suppress("UNUSED")
object MCCourseMod: ModInitializer {
    const val MOD_ID = "mccourse"
    override fun onInitialize() {
        ModItems.registerModItems()
        ModBlocks.registerModBlocks()

        ModRegistries.registerModStuffs()
    }
}