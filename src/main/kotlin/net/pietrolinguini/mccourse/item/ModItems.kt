package net.pietrolinguini.mccourse.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.item.custom.DowsingRodItem

object ModItems {

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(
            Registry.ITEM,
            Identifier(MCCourseMod.MOD_ID, name),
            item
        )
    }

    val ORICHALCUM_INGOT = registerItem(
        "orichalcum_ingot",
        Item(FabricItemSettings().group(ModItemGroups.COURSE))
    )
    val ORICHALCUM_NUGGET = registerItem(
        "orichalcum_nugget",
        Item(FabricItemSettings().group(ModItemGroups.COURSE))
    )
    val RAW_ORICHALCUM = registerItem(
        "raw_orichalcum",
        Item(FabricItemSettings().group(ModItemGroups.COURSE))
    )
    val DOWSING_ROD = registerItem(
        "dowsing_rod",
        DowsingRodItem(FabricItemSettings().group(ModItemGroups.COURSE).maxDamage(32))
    )


    fun registerModItems() {
        println("Registering Mod Items for " + MCCourseMod.MOD_ID)
    }
}