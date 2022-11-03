package net.pietrolinguini.mccourse.item

import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import java.util.function.Supplier

enum class ModToolMaterial(
    private var miningLevel: Int,
    private var itemDurability: Int,
    private var miningSpeed: Float,
    private var attackDamage: Float,
    private var enchantability: Int,
    private var repairIngredient: Supplier<Ingredient>
) : ToolMaterial {
    ORICHALCUM(MiningLevels.IRON, 450, 4.5f, 3.5f, 25, {
        Ingredient.ofItems(ModItems.ORICHALCUM_INGOT)
    });

    override fun getMiningLevel(): Int {
        return miningLevel
    }

    override fun getDurability(): Int {
        return itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return miningSpeed
    }

    override fun getAttackDamage(): Float {
        return attackDamage
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }

}