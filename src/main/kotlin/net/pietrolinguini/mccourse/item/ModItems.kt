package net.pietrolinguini.mccourse.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

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
        Item(FabricItemSettings().group(ItemGroup.MISC))
    )
    val ORICHALCUM_NUGGET = registerItem(
        "orichalcum_nugget",
        Item(FabricItemSettings().group(ItemGroup.MISC))
    )


    fun registerModItems() {
        println("Registering Mod Items for " + MCCourseMod.MOD_ID)
    }
}