package net.pietrolinguini.mccourse.entity.client

import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.TigerEntity
import software.bernie.geckolib3.model.AnimatedGeoModel

class TigerModel: AnimatedGeoModel<TigerEntity>() {
    override fun getModelLocation(`object`: TigerEntity?) =
        Identifier(MCCourseMod.MOD_ID, "geo/tiger.geo.json")

    override fun getTextureLocation(`object`: TigerEntity?) =
        Identifier(MCCourseMod.MOD_ID, "textures/entity/tiger/tiger.png")

    override fun getAnimationFileLocation(animatable: TigerEntity?) =
        Identifier(MCCourseMod.MOD_ID, "animations/tiger.animation.json")
}