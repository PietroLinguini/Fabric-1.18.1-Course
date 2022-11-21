package net.pietrolinguini.mccourse.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import java.util.stream.Stream

class OrichalcumBlasterBlock(settings: Settings?) : HorizontalFacingBlock(settings) {
    companion object {
        val FACING = Properties.HORIZONTAL_FACING

        val SHAPE_N = Stream.of(
            Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            Block.createCuboidShape(3.0, 2.0, 4.0, 13.0, 6.0, 5.0),
            Block.createCuboidShape(2.0, 0.0, 1.0, 14.0, 2.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 15.0, 13.0, 2.0, 16.0),
            Block.createCuboidShape(3.0, 2.0, 5.0, 13.0, 14.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 14.0, 13.0, 7.0, 15.0),
            Block.createCuboidShape(4.0, 13.0, 7.0, 12.0, 15.0, 13.0)
        ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }.get()

        val SHAPE_E = Stream.of(
            Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            Block.createCuboidShape(11.0, 2.0, 3.0, 12.0, 6.0, 13.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 15.0, 2.0, 14.0),
            Block.createCuboidShape(0.0, 0.0, 3.0, 1.0, 2.0, 13.0),
            Block.createCuboidShape(2.0, 2.0, 3.0, 11.0, 14.0, 13.0),
            Block.createCuboidShape(1.0, 0.0, 3.0, 2.0, 7.0, 13.0),
            Block.createCuboidShape(3.0, 13.0, 4.0, 9.0, 15.0, 12.0)
        ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }.get()


        val SHAPE_S = Stream.of(
            Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            Block.createCuboidShape(3.0, 2.0, 11.0, 13.0, 6.0, 12.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 2.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 0.0, 13.0, 2.0, 1.0),
            Block.createCuboidShape(3.0, 2.0, 2.0, 13.0, 14.0, 11.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 13.0, 7.0, 2.0),
            Block.createCuboidShape(4.0, 13.0, 3.0, 12.0, 15.0, 9.0)
        ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }.get()

        val SHAPE_W = Stream.of(
            Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            Block.createCuboidShape(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            Block.createCuboidShape(4.0, 2.0, 3.0, 5.0, 6.0, 13.0),
            Block.createCuboidShape(1.0, 0.0, 2.0, 14.0, 2.0, 14.0),
            Block.createCuboidShape(15.0, 0.0, 3.0, 16.0, 2.0, 13.0),
            Block.createCuboidShape(5.0, 2.0, 3.0, 14.0, 14.0, 13.0),
            Block.createCuboidShape(14.0, 0.0, 3.0, 15.0, 7.0, 13.0),
            Block.createCuboidShape(7.0, 13.0, 4.0, 13.0, 15.0, 12.0)
        ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }.get()
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when (state.get(FACING)) {
            Direction.NORTH -> SHAPE_N
            Direction.EAST -> SHAPE_E
            Direction.SOUTH -> SHAPE_S
            Direction.WEST -> SHAPE_W
            else -> SHAPE_N
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return defaultState.with(FACING, ctx.playerFacing?.opposite)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }
}