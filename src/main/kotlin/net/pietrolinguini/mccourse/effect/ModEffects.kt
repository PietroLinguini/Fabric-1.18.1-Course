package net.pietrolinguini.mccourse.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModEffects {
    val FREEZE = register("freeze", FreezeEffect(StatusEffectCategory.HARMFUL, 3124687))

    private fun register(name: String, statusEffect: StatusEffect) =
        Registry.register(Registry.STATUS_EFFECT, Identifier(MCCourseMod.MOD_ID, name), statusEffect)

    fun registerModEffects() {
        println("Registering Mod Effects for ${MCCourseMod.MOD_ID}")
    }
}