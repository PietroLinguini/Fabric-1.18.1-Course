package net.pietrolinguini.mccourse.screen.slot

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class ModResultSlot(inventory: Inventory, index: Int, x: Int, y: Int): Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack?): Boolean {
        return false
    }
}