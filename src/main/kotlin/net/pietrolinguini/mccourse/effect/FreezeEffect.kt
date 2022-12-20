package net.pietrolinguini.mccourse.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class FreezeEffect(statusEffectCategory: StatusEffectCategory, color: Int):
    StatusEffect(statusEffectCategory, color) {
    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if(!entity.world.isClient) {
            with (entity) {
                teleport(x, y, z)
                setVelocity(0.0, 0.0, 0.0)
            }

            super.applyUpdateEffect(entity, amplifier)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int) = true
}