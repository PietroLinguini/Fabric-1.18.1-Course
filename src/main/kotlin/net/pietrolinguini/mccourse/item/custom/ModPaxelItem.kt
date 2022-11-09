package net.pietrolinguini.mccourse.item.custom

import net.minecraft.item.*
import net.minecraft.util.ActionResult
import net.pietrolinguini.mccourse.util.ModTags

class ModPaxelItem(
    material: ToolMaterial?,
    attackDamage: Float,
    attackSpeed: Float,
    settings: Settings?
) : MiningToolItem(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings) {
    private val surrogateAxe = ModAxeItem(material, attackDamage, attackSpeed, settings)
    private val surrogateShovel = ShovelItem(material, attackDamage, attackSpeed, settings)

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        var result = surrogateAxe.useOnBlock(context)
        if (result == ActionResult.PASS)
            result = surrogateShovel.useOnBlock(context)
        return result
    }
}