package net.pietrolinguini.mccourse.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.custom.*
import net.pietrolinguini.mccourse.item.ModItemGroups
import net.pietrolinguini.mccourse.sound.ModSounds

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

    private fun registerBlockWithoutBlocKItem(name: String, block: Block): Block {
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
        ModStairsBlock(
            ORICHALCUM_BLOCK.defaultState,
            FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()
        ),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_SLAB = registerBlock(
        "orichalcum_slab",
        SlabBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_BUTTON = registerBlock(
        "orichalcum_button",
        ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_PRESSURE_PLATE = registerBlock(
        "orichalcum_pressure_plate",
        ModPressurePlateBlock(
            PressurePlateBlock.ActivationRule.EVERYTHING,
            FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()
        ),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_FENCE = registerBlock(
        "orichalcum_fence",
        FenceBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_FENCE_GATE = registerBlock(
        "orichalcum_fence_gate",
        FenceGateBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_WALL = registerBlock(
        "orichalcum_wall",
        WallBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()),
        ModItemGroups.COURSE
    )
    val CHERRY_BLOSSOM_DOOR = registerBlock(
        "cherry_blossom_door",
        ModDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(5.5f).requiresTool().nonOpaque()),
        ModItemGroups.COURSE
    )
    val CHERRY_BLOSSOM_TRAPDOOR = registerBlock(
        "cherry_blossom_trapdoor",
        ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(5.5f).requiresTool().nonOpaque()),
        ModItemGroups.COURSE
    )
    val ORICHALCUM_LAMP = registerBlock(
        "orichalcum_lamp",
        OrichalcumLampBlock(FabricBlockSettings.of(Material.METAL).strength(5.5f).requiresTool()
            .luminance { if (it.get(OrichalcumLampBlock.CLICKED)) 15 else 0 }.sounds(ModSounds.ORICHALCUM_SOUNDS)
        ),
        ModItemGroups.COURSE
    )

    val TURNIP_CROP = registerBlockWithoutBlocKItem(
        "turnip_crop",
        ModTurnipBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)),
    )

    val PINK_ROSE = registerBlock(
        "pink_rose",
        FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.PINK_TULIP)),
        ModItemGroups.COURSE
    )

    val POTTED_PINK_ROSE = registerBlockWithoutBlocKItem(
        "potted_pink_rose",
        FlowerPotBlock(PINK_ROSE, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)),
    )

    fun registerModBlocks() {
        println("Registering Mod Blocks for " + MCCourseMod.MOD_ID)
    }
}