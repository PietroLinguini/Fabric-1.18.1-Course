package net.pietrolinguini.mccourse

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry
import net.minecraft.client.render.RenderLayer
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.entity.ModEntities
import net.pietrolinguini.mccourse.entity.client.RaccoonRenderer
import net.pietrolinguini.mccourse.entity.client.TigerRenderer
import net.pietrolinguini.mccourse.event.ReplaceTitleScreenEvent
import net.pietrolinguini.mccourse.fluid.ModFluids
import net.pietrolinguini.mccourse.screen.ModScreenHandlers
import net.pietrolinguini.mccourse.screen.OrichalcumBlasterScreen
import net.pietrolinguini.mccourse.util.ModModelPredicateProvider

object MCCourseClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_TRAPDOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TURNIP_CROP, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_ROSE, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PINK_ROSE, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORICHALCUM_BLASTER, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_LEAVES, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_BLOSSOM_SAPLING, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_WINDOW, RenderLayer.getTranslucent())

        ModModelPredicateProvider.registerModModels()

        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.HONEY_STILL, SimpleFluidRenderHandler(
                SimpleFluidRenderHandler.WATER_STILL,
                SimpleFluidRenderHandler.WATER_FLOWING,
                SimpleFluidRenderHandler.WATER_OVERLAY,
                0xe9860c
            )
        )
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.HONEY_FLOWING, SimpleFluidRenderHandler(
                SimpleFluidRenderHandler.WATER_STILL,
                SimpleFluidRenderHandler.WATER_FLOWING,
                SimpleFluidRenderHandler.WATER_OVERLAY,
                0xe9860c
            )
        )

        ScreenRegistry.register(ModScreenHandlers.ORICHALCUM_BLASTER_SCREEN_HANDLER, ::OrichalcumBlasterScreen)

        ScreenEvents.BEFORE_INIT.register(ReplaceTitleScreenEvent())

        EntityRendererRegistry.register(ModEntities.RACCOON, ::RaccoonRenderer)
        EntityRendererRegistry.register(ModEntities.TIGER, ::TigerRenderer)
    }
}