package net.pietrolinguini.mccourse.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModBlocks {

    private fun registerBlockItem(name: String, block: Block, group: ItemGroup): Item {
        return Registry.register(
            Registry.ITEM,
            Identifier(MCCourseMod.MOD_ID, name),
            BlockItem(block, FabricItemSettings().group(group))
        )
    }

    private fun registerBlock(name: String, block: Block, group: ItemGroup): Block {
        registerBlockItem(name, block, group)
        return Registry.register(
            Registry.BLOCK,
            Identifier(MCCourseMod.MOD_ID, name),
            block
        )
    }

    val ORICHALCUM_BLOCK = registerBlock(
        "orichalcum_block",
        Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),
        ItemGroup.MISC
    )
    val ORICHALCUM_ORE = registerBlock(
        "orichalcum_ore",
        Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()),
        ItemGroup.MISC
    )

    fun registerModBlocks() {
        println("Registering Mod Blocks for " + MCCourseMod.MOD_ID)
    }
}