package net.pietrolinguini.mccourse

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import net.pietrolinguini.mccourse.block.ModBlocks

object MCCourseClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_TRAPDOOR, RenderLayer.getCutout())
    }
}