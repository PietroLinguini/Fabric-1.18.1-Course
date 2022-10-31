package net.pietrolinguini.mccourse.item

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod

object ModItemGroups {
    val COURSE: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MCCourseMod.MOD_ID, "course")) {
        ItemStack(ModItems.ORICHALCUM_INGOT)
    }
}