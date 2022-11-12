package net.pietrolinguini.mccourse.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class OrichalcumLampBlock(settings: Settings) : Block(settings) {
    companion object {
        val CLICKED: BooleanProperty = BooleanProperty.of("clicked")
    }

    init {
        this.defaultState = this.defaultState.with(CLICKED, false)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            val clicked = state.get(CLICKED)
            world.setBlockState(pos, state.with(CLICKED, !clicked), 3)
            return ActionResult.SUCCESS
        }

        return ActionResult.PASS
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(CLICKED)
    }
}