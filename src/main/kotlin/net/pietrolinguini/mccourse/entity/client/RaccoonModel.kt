package net.pietrolinguini.mccourse.entity.client

import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import software.bernie.geckolib3.model.AnimatedGeoModel

class RaccoonModel: AnimatedGeoModel<RaccoonEntity>() {
    override fun getModelLocation(`object`: RaccoonEntity?): Identifier =
        Identifier(MCCourseMod.MOD_ID, "geo/raccoon.geo.json")

    override fun getTextureLocation(`object`: RaccoonEntity?): Identifier =
        Identifier(MCCourseMod.MOD_ID, "textures/entity/raccoon/raccoon.png")

    override fun getAnimationFileLocation(animatable: RaccoonEntity?): Identifier =
        Identifier(MCCourseMod.MOD_ID, "animations/raccoon.animation.json")
}