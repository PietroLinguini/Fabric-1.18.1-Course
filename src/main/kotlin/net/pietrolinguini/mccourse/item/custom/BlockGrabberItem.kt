package net.pietrolinguini.mccourse.item.custom

import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import net.pietrolinguini.mccourse.item.ModItems

class BlockGrabberItem(settings: Settings?) : Item(settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (user.getStackInHand(Hand.MAIN_HAND).hasNbt()) {
            // Resets NBT data for the tablet
            user.getStackInHand(Hand.MAIN_HAND).nbt = NbtCompound()
        }

        return super.use(world, user, hand)
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.player == null) return ActionResult.FAIL

        val player = context.player!!

        if (player.getStackInHand(Hand.MAIN_HAND)?.item == ModItems.BLOCK_GRABBER) {
            val grabber = player.getStackInHand(Hand.MAIN_HAND)!!
            val posClicked = context.blockPos
            val block = context.world.getBlockState(posClicked).block

            context.world.breakBlock(posClicked, false, player)
            val nbt = NbtCompound()
            nbt.putString("mccourse.block_grabbed", "Grabbed ${block.asItem().name.string}")
            grabber.nbt = nbt
        }

        return ActionResult.SUCCESS
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        if (stack.hasNbt()) {
            val block = stack.nbt?.getString("mccourse.block_grabbed")
            tooltip?.add(LiteralText(block))
        }
    }
}