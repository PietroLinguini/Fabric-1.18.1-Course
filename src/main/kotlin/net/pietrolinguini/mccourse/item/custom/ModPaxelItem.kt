package net.pietrolinguini.mccourse.item.custom

import net.minecraft.item.ItemUsageContext
import net.minecraft.item.MiningToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.ActionResult
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.util.ModTags

class ModPaxelItem(
    material: ToolMaterial?,
    attackDamage: Float,
    attackSpeed: Float,
    settings: Settings?
) : MiningToolItem(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        var result = ModItems.ORICHALCUM_AXE.useOnBlock(context)
        if (result == ActionResult.PASS)
            result = ModItems.ORICHALCUM_SHOVEL.useOnBlock(context)
        return result
    }
}