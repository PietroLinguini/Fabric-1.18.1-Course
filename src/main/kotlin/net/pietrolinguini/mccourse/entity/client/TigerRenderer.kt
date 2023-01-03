package net.pietrolinguini.mccourse.entity.client

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.TigerEntity
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class TigerRenderer(ctx: EntityRendererFactory.Context): GeoEntityRenderer<TigerEntity>(ctx, TigerModel()) {
    init {
        shadowRadius = 0.3f
    }

    override fun getTextureLocation(entity: TigerEntity) =
        Identifier(MCCourseMod.MOD_ID, "textures/entity/tiger/tiger.png")
}