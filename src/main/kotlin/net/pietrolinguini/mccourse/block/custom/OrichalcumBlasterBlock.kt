package net.pietrolinguini.mccourse.block.custom

import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.*
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.pietrolinguini.mccourse.block.entity.ModBlockEntities
import net.pietrolinguini.mccourse.block.entity.OrichalcumBlasterEntity
import java.util.stream.Stream

class OrichalcumBlasterBlock(settings: Settings?) : BlockWithEntity(settings), BlockEntityProvider {
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

    override fun getPlacementState(ctx: ItemPlacementContext) =
        defaultState.with(FACING, ctx.playerFacing?.opposite)
    override fun rotate(state: BlockState, rotation: BlockRotation) =
        state.with(FACING, rotation.rotate(state.get(FACING)))

    override fun mirror(state: BlockState, mirror: BlockMirror) =
        state.rotate(mirror.getRotation(state.get(FACING)))

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getRenderType(state: BlockState?) = BlockRenderType.MODEL

    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        moved: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is OrichalcumBlasterEntity) {
                ItemScatterer.spawn(world, pos, blockEntity)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory)
            }
        }

        return ActionResult.SUCCESS
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState) = OrichalcumBlasterEntity(pos, state)

    override fun <T : BlockEntity> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        println("givenType: $type")
        println("expectedType: ${ModBlockEntities.ORICHALCUM_BLASTER}")
        return checkType(type, ModBlockEntities.ORICHALCUM_BLASTER, OrichalcumBlasterEntity.tick)
    }
}