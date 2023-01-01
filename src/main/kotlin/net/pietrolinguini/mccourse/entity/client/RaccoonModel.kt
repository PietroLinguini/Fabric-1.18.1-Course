package net.pietrolinguini.mccourse.entity.client

import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import software.bernie.geckolib3.model.AnimatedGeoModel

class RaccoonModel: AnimatedGeoModel<RaccoonEntity>() {
    override fun getModelLocation(entity: RaccoonEntity): Identifier =
        Identifier(MCCourseMod.MOD_ID, "geo/raccoon.geo.json")

    override fun getTextureLocation(entity: RaccoonEntity): Identifier =
        RaccoonRenderer.LOCATION_BY_VARIANT[entity.getVariant()]!!

    override fun getAnimationFileLocation(entity: RaccoonEntity): Identifier =
        Identifier(MCCourseMod.MOD_ID, "animations/raccoon.animation.json")
}