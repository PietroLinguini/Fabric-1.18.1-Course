package net.pietrolinguini.mccourse.item.custom

import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class DataTabletItem(settings: Settings?) : Item(settings) {
    override fun hasGlint(stack: ItemStack): Boolean {
        return stack.hasNbt()
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (user.getStackInHand(Hand.MAIN_HAND).hasNbt()) {
            // Resets NBT data for the tablet
            user.getStackInHand(Hand.MAIN_HAND).nbt = NbtCompound()
        }

        return super.use(world, user, hand)
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        if (stack.hasNbt()) {
            val currentOre = stack.nbt?.getString("mccourse.last_ore")
            tooltip.add(LiteralText(currentOre))
        }
    }
}