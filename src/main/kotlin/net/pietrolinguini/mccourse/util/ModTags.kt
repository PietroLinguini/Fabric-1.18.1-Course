package net.pietrolinguini.mccourse.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModTags {
    object Blocks {
        val DOWSING_ROD_DETECTABLE_BLOCKS = createTag("dowsing_rod_detectable_blocks")
        val PAXEL_MINEABLE = createTag("mineable/paxel")
        val ORICHALCUM_BLOCKS = createCommonTag("orichalcum_blocks")
        val ORICHALCUM_ORES = createCommonTag("orichalcum_ores")

        private fun createTag(name: String): TagKey<Block> {
            return TagKey.of(Registry.BLOCK_KEY, Identifier(MCCourseMod.MOD_ID, name))
        }

        private fun createCommonTag(name: String): TagKey<Block> {
            return TagKey.of(Registry.BLOCK_KEY, Identifier("c", name))
        }
    }

    object Items {
        val ORICHALCUM_INGOTS = createCommonTag("orichalcum_ingots")
        val ORICHALCUM_NUGGETS = createCommonTag("orichalcum_nuggets")

        private fun createTag(name: String): TagKey<Item> {
            return TagKey.of(Registry.ITEM_KEY, Identifier(MCCourseMod.MOD_ID, name))
        }

        private fun createCommonTag(name: String): TagKey<Item> {
            return TagKey.of(Registry.ITEM_KEY, Identifier("c", name))
        }
    }
}