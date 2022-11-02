package net.pietrolinguini.mccourse.item

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent

object ModFoodComponents {
    val TURNIP: FoodComponent = FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
        .statusEffect(StatusEffectInstance(StatusEffects.REGENERATION, 200), 1f).build()
}