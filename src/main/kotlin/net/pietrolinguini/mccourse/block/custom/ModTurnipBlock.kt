package net.pietrolinguini.mccourse.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemConvertible
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.pietrolinguini.mccourse.item.ModItems
import java.util.*

class ModTurnipBlock(settings: Settings?) : CropBlock(settings) {
    companion object {
        val AGE = Properties.AGE_3
        val AGE_TO_SHAPE = arrayOf(
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
        )
    }

    override fun getAgeProperty(): IntProperty {
        return AGE
    }

    override fun getMaxAge(): Int {
        return 3
    }

    override fun getSeedsItem(): ItemConvertible {
        return ModItems.TURNIP_SEEDS
    }

    @Deprecated("Deprecated in Java")
    override fun randomTick(state: BlockState?, world: ServerWorld?, pos: BlockPos?, random: Random?) {
        if (Random().nextInt(3) != 0) {
            super.randomTick(state, world, pos, random)
        }
    }

    override fun getGrowthAmount(world: World?): Int {
        return super.getGrowthAmount(world) / 3
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return AGE_TO_SHAPE[state.get(ageProperty)]
    }
}