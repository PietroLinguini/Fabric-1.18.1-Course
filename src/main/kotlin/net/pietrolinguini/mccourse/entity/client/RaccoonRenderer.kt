package net.pietrolinguini.mccourse.entity.client

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class RaccoonRenderer(ctx: EntityRendererFactory.Context): GeoEntityRenderer<RaccoonEntity>(ctx, RaccoonModel()) {
    override fun getTextureLocation(animatable: RaccoonEntity?): Identifier =
        Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoon.png")
}