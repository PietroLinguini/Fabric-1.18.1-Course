package net.pietrolinguini.mccourse.entity.client.armor

import net.pietrolinguini.mccourse.item.custom.OrichalcumArmorItem
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer

class OrichalcumArmorRenderer: GeoArmorRenderer<OrichalcumArmorItem>(OrichalcumArmorModel()) {
    init {
        headBone = "armorHead"
        bodyBone = "armorBody"
        rightArmBone = "armorRightArm"
        leftArmBone = "armorLeftArm"
        rightLegBone = "armorLeftLeg"
        leftLegBone = "armorRightLeg"
        rightBootBone = "armorLeftBoot"
        leftBootBone = "armorRightBoot"
    }
}