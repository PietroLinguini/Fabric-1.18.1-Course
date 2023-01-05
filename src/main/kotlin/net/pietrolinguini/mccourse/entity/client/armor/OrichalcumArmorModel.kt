package net.pietrolinguini.mccourse.entity.client.armor

import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.item.custom.OrichalcumArmorItem
import software.bernie.geckolib3.model.AnimatedGeoModel

class OrichalcumArmorModel: AnimatedGeoModel<OrichalcumArmorItem>() {
    override fun getModelLocation(`object`: OrichalcumArmorItem?) =
        Identifier(MCCourseMod.MOD_ID, "geo/orichalcum_armor.geo.json")

    override fun getTextureLocation(`object`: OrichalcumArmorItem?) =
        Identifier(MCCourseMod.MOD_ID, "textures/model/armor/orichalcum_armor_texture.png")

    override fun getAnimationFileLocation(animatable: OrichalcumArmorItem?) =
        Identifier(MCCourseMod.MOD_ID, "animations/armor_animation.json")
}