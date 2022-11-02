package net.pietrolinguini.mccourse.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.SlabBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.custom.ModStairsBlock
import net.pietrolinguini.mccourse.block.custom.SpeedyBlock
import net.pietrolinguini.mccourse.item.ModItemGroups

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
        ModItemGroups.COURSE
    )
    val ORICHALCUM_ORE = registerBlock(
        "orichalcum_ore",
        Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val DEEPSLATE_ORICHALCUM_ORE = registerBlock(
        "deepslate_orichalcum_ore",
        Block(FabricBlockSettings.of(Material.STONE).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val RAW_ORICHALCUM_BLOCK = registerBlock(
        "raw_orichalcum_block",
        Block(FabricBlockSettings.of(Material.STONE).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val SPEEDY_BLOCK = registerBlock(
        "speedy_block",
        SpeedyBlock(FabricBlockSettings.of(Material.STONE).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_STAIRS = registerBlock(
        "orichalcum_stairs",
        ModStairsBlock(ORICHALCUM_BLOCK.defaultState, FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_SLAB = registerBlock(
        "orichalcum_slab",
        SlabBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )

    fun registerModBlocks() {
        println("Registering Mod Blocks for " + MCCourseMod.MOD_ID)
    }
}