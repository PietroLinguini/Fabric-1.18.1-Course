package net.pietrolinguini.mccourse.block.entity

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.pietrolinguini.mccourse.item.inventory.ImplementedInventory
import net.pietrolinguini.mccourse.recipe.OrichalcumBlasterRecipe
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
                if (isConsumingFuel()) fuelTime--

                if (hasRecipe()) {
                    if (hasFuelInSlot() && !isConsumingFuel())
                        consumeFuel()
                    if (isConsumingFuel()) {
                        progress++
                        if (progress > maxProgress)
                            craftItem()
                    }
                }
                else resetProgress()
            }
        }
        private fun canInsertItemIntoOutputSlot(inventory: SimpleInventory, output: ItemStack) =
            inventory.getStack(3).item == output.item || inventory.getStack(3).isEmpty
        private fun canInsertAmountIntoOutputSlot(inventory: SimpleInventory) =
            inventory.getStack(3).maxCount > inventory.getStack(3).count
    }

    protected val propertyDelegate: PropertyDelegate
    private var progress = 0
    private var maxProgress = 72
    private var fuelTime = 0
    private var maxFuelTime = 0

    init {
        propertyDelegate = object: PropertyDelegate {
            override fun get(index: Int): Int =
                when (index) {
                    0 -> progress
                    1 -> maxProgress
                    2 -> fuelTime
                    3 -> maxFuelTime
                    else -> 0
                }

            override fun set(index: Int, value: Int) {
                when (index) {
                    0 -> progress = value
                    1 -> maxProgress = value
                    2 -> fuelTime = value
                    3 -> maxFuelTime = value
                }
            }

            override fun size() = 4
        }
    }

    private val inventory =
        DefaultedList.ofSize(4, ItemStack.EMPTY)

    override fun getItems(): DefaultedList<ItemStack?> = inventory

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler =
        OrichalcumBlasterScreenHandler(syncId, inv, this, propertyDelegate)

    override fun getDisplayName(): Text = LiteralText("Orichalcum Blaster")

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        nbt.putInt("blaster.progress", progress)
        nbt.putInt("blaster.fuelTime", fuelTime)
        nbt.putInt("blaster.maxFuelTime", maxFuelTime)
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
        progress = nbt.getInt("blaster.progress")
        fuelTime = nbt.getInt("blaster.fuelTime")
        maxFuelTime = nbt.getInt("blaster.maxFuelTime")
    }

    private fun consumeFuel() {
        if (!getStack(0).isEmpty) {
            fuelTime = FuelRegistry.INSTANCE[removeStack(0, 1).item]
            maxFuelTime = fuelTime
        }
    }

    fun resetProgress() { progress = 0 }

    private fun hasRecipe(): Boolean {
        val inventory = SimpleInventory(inventory.size)
        for (i in 0 until inventory.size()) {
            inventory.setStack(i, getStack(i))
        }

        // Whether the items in 'inventory' contain a recipe that is the type of OrichalcumBlasterRecipe
        val match = world?.recipeManager?.getFirstMatch(OrichalcumBlasterRecipe.Type, inventory, world)!!

        return match.isPresent && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().output)
    }

    private fun hasNotReachedStackLimit() =
        getStack(3).count < getStack(3).maxCount
    private fun hasFuelInSlot() = !getStack(0).isEmpty
    private fun isConsumingFuel() = fuelTime > 0

    private fun craftItem() {
        val inventory = SimpleInventory(inventory.size)
        for (i in 0 until inventory.size()) {
            inventory.setStack(i, getStack(i))
        }

        // Whether the items in 'inventory' contain a recipe that is the type of OrichalcumBlasterRecipe
        val match = world?.recipeManager?.getFirstMatch(OrichalcumBlasterRecipe.Type, inventory, world)!!

        if (match.isPresent) {
            removeStack(1, 1)
            removeStack(2, 1)

            setStack(3, ItemStack(match.get().output.item, getStack(3).count + 1))

            resetProgress()
        }
    }

    override fun markDirty() {
        super<BlockEntity>.markDirty()
    }
}