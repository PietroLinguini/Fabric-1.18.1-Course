package net.pietrolinguini.mccourse.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.world.World
import net.pietrolinguini.mccourse.screen.slot.ModFuelSlot
import net.pietrolinguini.mccourse.screen.slot.ModResultSlot

class OrichalcumBlasterScreenHandler: ScreenHandler {
    private val inventory: Inventory
    private val world: World

    constructor(syncId: Int, playerInventory: PlayerInventory): this(syncId, playerInventory, SimpleInventory(4))

    constructor(syncId: Int, playerInventory: PlayerInventory, inventory: Inventory):
            super(ModScreenHandlers.ORICHALCUM_BLASTER_SCREEN_HANDLER, syncId) {
        checkSize(inventory, 4)
        this.inventory = inventory
        this.world = playerInventory.player.world
        inventory.onOpen(playerInventory.player)

        // Our Slots
        addSlot(ModFuelSlot(inventory, 0, 18, 50))
        addSlot(Slot(inventory, 1, 66, 16))
        addSlot(Slot(inventory, 2, 66, 50))
        addSlot(ModResultSlot(inventory, 3, 114, 33))

        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)
    }

    override fun canUse(player: PlayerEntity?) = inventory.canPlayerUse(player)

    override fun transferSlot(player: PlayerEntity?, invSlot: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot = slots[invSlot]

        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()

            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack, 4, slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, 4, false)) {
                return ItemStack.EMPTY
            }

            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }

        return newStack
    }

    private fun addPlayerInventory(playerInventory: PlayerInventory) {
        for (row in 0..2) {
            for (column in 0..8) {
                addSlot(Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 86 + row * 18))
            }
        }
    }

    private fun addPlayerHotbar(playerInventory: PlayerInventory) {
        for (column in 0..8) {
            addSlot(Slot(playerInventory, column, 8 + column * 18, 144))
        }
    }
}