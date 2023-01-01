package net.pietrolinguini.mccourse.entity.client

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import net.pietrolinguini.mccourse.entity.variants.RaccoonVariant
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class RaccoonRenderer(ctx: EntityRendererFactory.Context): GeoEntityRenderer<RaccoonEntity>(ctx, RaccoonModel()) {
    companion object {
        val LOCATION_BY_VARIANT: Map<RaccoonVariant, Identifier> =
            mapOf(
                RaccoonVariant.DEFAULT to Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoon.png"),
                RaccoonVariant.DARK to Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoondark.png"),
                RaccoonVariant.RED to Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/redraccoon.png"),
            )
//            Util.make(Maps.newEnumMap(RaccoonVariant::class.java)) {
//                it[RaccoonVariant.DEFAULT] = Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoon.png")
//                it[RaccoonVariant.DARK] = Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoondark.png")
//                it[RaccoonVariant.RED] = Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/redraccoon.png")
//            }
    }

    override fun getTextureLocation(entity: RaccoonEntity): Identifier =
        LOCATION_BY_VARIANT[entity.getVariant()]!!
}