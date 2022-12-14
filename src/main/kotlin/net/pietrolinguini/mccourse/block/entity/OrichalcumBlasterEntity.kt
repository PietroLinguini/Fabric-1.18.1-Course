package net.pietrolinguini.mccourse.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.item.inventory.ImplementedInventory
import net.pietrolinguini.mccourse.screen.OrichalcumBlasterScreenHandler


class OrichalcumBlasterEntity(pos: BlockPos, state: BlockState) : BlockEntity(
    ModBlockEntities.ORICHALCUM_BLASTER,
    pos,
    state
), NamedScreenHandlerFactory, ImplementedInventory {
    companion object {
//        fun tick(world: World, pos: BlockPos, state: BlockState, entity: OrichalcumBlasterEntity) {
//            if (hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
//                craftItem(entity)
//            }
//        }

        val tick = BlockEntityTicker { world, pos, state, entity: OrichalcumBlasterEntity ->
            with (entity) {
                if (hasRecipe() && hasNotReachedStackLimit()) {
                    craftItem()
                }
            }
        }

        private fun OrichalcumBlasterEntity.craftItem() {
            removeStack(0, 1)
            removeStack(1, 1)
            removeStack(2, 1)

            setStack(
                3, ItemStack(
                    ModItems.ORICHALCUM_PICKAXE,
                    getStack(3).count + 1
                )
            )
        }

        private fun OrichalcumBlasterEntity.hasRecipe(): Boolean {
            val hasItemInFirstSlot = getStack(0).item == ModItems.COAL_SLIVER
            val hasItemInSecondSlot = getStack(1).item == Items.GOLDEN_PICKAXE
            val hasItemInThirdSlot = getStack(2).item == ModItems.ORICHALCUM_INGOT

            return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot
        }

        private fun OrichalcumBlasterEntity.hasNotReachedStackLimit() =
            getStack(3).count < getStack(3).maxCount
    }

    private val inventory =
        DefaultedList.ofSize(4, ItemStack.EMPTY)

    override fun getItems(): DefaultedList<ItemStack?> = inventory

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler =
        OrichalcumBlasterScreenHandler(syncId, inv, this)

    override fun getDisplayName(): Text = LiteralText("Orichalcum Blaster")

    override fun writeNbt(nbt: NbtCompound?) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
    }

    override fun readNbt(nbt: NbtCompound?) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
    }

    override fun markDirty() {
        super<BlockEntity>.markDirty()
    }
}