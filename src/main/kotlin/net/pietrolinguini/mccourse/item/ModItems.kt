package net.pietrolinguini.mccourse.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.ArmorItem
import net.minecraft.item.BowItem
import net.minecraft.item.BucketItem
import net.minecraft.item.HorseArmorItem
import net.minecraft.item.Item
import net.minecraft.item.ShovelItem
import net.minecraft.item.SignItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.fluid.ModFluids
import net.pietrolinguini.mccourse.item.custom.*
import net.pietrolinguini.mccourse.sound.ModSounds

object ModItems {

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(
            Registry.ITEM,
            Identifier(MCCourseMod.MOD_ID, name),
            item
        )
    }

    @JvmField
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
    val TURNIP = registerItem(
        "turnip",
        Item(FabricItemSettings().group(ModItemGroups.COURSE).food(ModFoodComponents.TURNIP))
    )
    val COAL_SLIVER = registerItem(
        "coal_sliver",
        Item(FabricItemSettings().group(ModItemGroups.COURSE))
    )

    val ORICHALCUM_PICKAXE = registerItem(
        "orichalcum_pickaxe",
        ModPickaxeItem(
            ModToolMaterial.ORICHALCUM,
            1,
            2f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_AXE = registerItem(
        "orichalcum_axe",
        ModAxeItem(
            ModToolMaterial.ORICHALCUM,
            3f,
            1f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_HOE = registerItem(
        "orichalcum_hoe",
        ModHoeItem(
            ModToolMaterial.ORICHALCUM,
            0,
            0f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_SHOVEL = registerItem(
        "orichalcum_shovel",
        ShovelItem(
            ModToolMaterial.ORICHALCUM,
            1f,
            2f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_SWORD = registerItem(
        "orichalcum_sword",
        ModSlowingSwordItem(
            ModToolMaterial.ORICHALCUM,
            3,
            3f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )

    val ORICHALCUM_PAXEL = registerItem(
        "orichalcum_paxel",
        ModPaxelItem(
            ModToolMaterial.ORICHALCUM,
            1f,
            1f,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )


    val ORICHALCUM_HELMET = registerItem(
        "orichalcum_helmet",
        ModArmorItem(
            ModArmorMaterial.ORICHALCUM,
            EquipmentSlot.HEAD,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_CHESTPLATE = registerItem(
        "orichalcum_chestplate",
        ArmorItem(
            ModArmorMaterial.ORICHALCUM,
            EquipmentSlot.CHEST,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_LEGGINGS = registerItem(
        "orichalcum_leggings",
        ArmorItem(
            ModArmorMaterial.ORICHALCUM,
            EquipmentSlot.LEGS,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )
    val ORICHALCUM_BOOTS = registerItem(
        "orichalcum_boots",
        ArmorItem(
            ModArmorMaterial.ORICHALCUM,
            EquipmentSlot.FEET,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )

    val ORICHALCUM_HORSE_ARMOR = registerItem(
        "orichalcum_horse_armor",
        HorseArmorItem(
            15,
            "orichalcum",
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )

    val DATA_TABLET = registerItem(
        "data_tablet",
        DataTabletItem(
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val BLOCK_GRABBER = registerItem(
        "block_grabber",
        BlockGrabberItem(
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val TURNIP_SEEDS = registerItem(
        "turnip_seeds",
        AliasedBlockItem(
            ModBlocks.TURNIP_CROP,
            FabricItemSettings().group(ModItemGroups.COURSE)
        )
    )

    val BAR_BRAWL_MUSIC_DISC = registerItem(
        "bar_brawl_music_disc",
        ModMusicDiscItem(
            9,
            ModSounds.BAR_BRAWL,
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val ORICHALCUM_STAFF = registerItem(
        "orichalcum_staff",
        Item(
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val ORICHALCUM_BOW = registerItem(
        "orichalcum_bow",
        BowItem(
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val HONEY_BUCKET = registerItem(
        "honey_bucket",
        BucketItem(
            ModFluids.HONEY_STILL,
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(1)
        )
    )

    val CHERRY_BLOSSOM_SIGN = registerItem(
        "cherry_blossom_sign",
        SignItem(
            FabricItemSettings().group(ModItemGroups.COURSE).maxCount(16),
            ModBlocks.CHERRY_BLOSSOM_SIGN_BLOCK,
            ModBlocks.CHERRY_BLOSSOM_WALL_SIGN_BLOCK
        )
    )

    fun registerModItems() {
        println("Registering Mod Items for " + MCCourseMod.MOD_ID)
    }
}