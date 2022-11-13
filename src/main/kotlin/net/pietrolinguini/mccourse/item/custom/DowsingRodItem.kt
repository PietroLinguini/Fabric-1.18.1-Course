package net.pietrolinguini.mccourse.item.custom

import net.minecraft.block.Block
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.util.InventoryUtil.getFirstInventoryIndex
import net.pietrolinguini.mccourse.util.InventoryUtil.hasStackInInventory
import net.pietrolinguini.mccourse.util.ModTags

class DowsingRodItem(settings: Settings?) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient) {
            val positionClicked = context.blockPos
            val player = context.player
            var foundBlock = false

            for (i in 0..positionClicked.y) {
                val blockBelow = context.world.getBlockState(positionClicked.down(i)).block

                if (blockBelow.isValuableBlock()) {
                    outputValuableCoordinates(player, blockBelow, positionClicked.down(i))
                    foundBlock = true

                    if (player != null && player.hasStackInInventory(ModItems.DATA_TABLET)) {
                        player.addNbtToDataTablet(positionClicked.add(0, -i, 0), blockBelow)
                    }

                    break
                }
            }

            if (!foundBlock)
                player?.sendMessage(TranslatableText("item.mccourse.dowsing_rod.no_valuables"), false)

        }

        context.stack.damage(1, context .player) { it?.sendToolBreakStatus(it.activeHand) }

        return ActionResult.SUCCESS
    }

    private fun PlayerEntity.addNbtToDataTablet(pos: BlockPos, blockBelow: Block) {
        val dataTablet = inventory.getStack(getFirstInventoryIndex(ModItems.DATA_TABLET))
        val nbtData = NbtCompound()
        nbtData.putString("mccourse.last_ore", "Found ${blockBelow.asItem().name.string}" +
                " at (${pos.x}, ${pos.y}, ${pos.z})")
        dataTablet.nbt = nbtData
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        if (Screen.hasShiftDown()) {
            tooltip?.add(TranslatableText("item.mccourse.dowsing_rode_tooltip.shift"))
        } else {
            tooltip?.add(TranslatableText("item.mccourse.dowsing_rode_tooltip"))
        }
    }

    private fun outputValuableCoordinates(
        player: PlayerEntity?,
        blockBelow: Block,
        blockPos: BlockPos
    ) {
        player?.sendMessage(
            LiteralText("Found ${blockBelow.asItem().name.string} at (${blockPos.x}, ${blockPos.y}, ${blockPos.z})"),
            false
        )
    }

    private fun Block.isValuableBlock(): Boolean {
        return ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS.contains(this)
    }
}