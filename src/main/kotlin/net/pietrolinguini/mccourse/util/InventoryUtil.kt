package net.pietrolinguini.mccourse.util

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object InventoryUtil {
    fun PlayerEntity.hasStackInInventory(item: Item): Boolean {
        for (i in 0 until inventory.size()) {
            val currentStack = inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isItemEqual(ItemStack(item))) {
                return true
            }
        }
        return false
    }

    fun PlayerEntity.getFirstInventoryIndex(item: Item): Int {
        for (i in 0 until inventory.size()) {
            val currentStack = inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isItemEqual(ItemStack(item))) {
                return i
            }
        }
        return -1
    }
}