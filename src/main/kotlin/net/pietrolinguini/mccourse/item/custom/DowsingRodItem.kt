package net.pietrolinguini.mccourse.item.custom

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos

class DowsingRodItem(settings: Settings?) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        if (context == null) {
            System.err.println("context is null for some reason")
            return ActionResult.FAIL
        }

        if (context.world.isClient) {
            val positionClicked = context.blockPos
            val player = context.player
            var foundBlock = false

            for (i in 0..positionClicked.y) {
                val blockBelow = context.world.getBlockState(positionClicked.down(i)).block

                if (blockBelow.isValuableBlock()) {
                    outputValuableCoordinates(player, blockBelow, positionClicked.down(i))
                    foundBlock = true
                    break
                }
            }

            if (!foundBlock)
                player?.sendMessage(TranslatableText("item.mccourse.dowsing_rod.no_valuables"), false)

        }

        context.stack.damage(1, context.player) { it?.sendToolBreakStatus(it.activeHand) }

        return super.useOnBlock(context)
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
        return this in setOf(Blocks.COAL_BLOCK, Blocks.COPPER_BLOCK, Blocks.DIAMOND_ORE, Blocks.IRON_ORE)
    }
}