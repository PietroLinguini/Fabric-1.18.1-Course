package net.pietrolinguini.mccourse.potion

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.effect.ModEffects
import net.pietrolinguini.mccourse.item.ModItems
import net.pietrolinguini.mccourse.mixins.BrewingRecipeRegistryMixin

object ModPotions {
    val FREEZE_POTION = register("freeze_potion", ModEffects.FREEZE, 200, 0)

    private fun register(name: String, statusEffect: StatusEffect, duration: Int, amplifier: Int) =
        Registry.register(Registry.POTION, Identifier(MCCourseMod.MOD_ID, name),
            Potion(StatusEffectInstance(statusEffect, duration, amplifier)))

    private fun registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegistryPotionRecipe(Potions.AWKWARD, ModItems.ORICHALCUM_INGOT, FREEZE_POTION)
    }

    fun registerPotions() {
        println("Registering Mod Potions for " + MCCourseMod.MOD_ID)

        registerPotionRecipes()
    }
}