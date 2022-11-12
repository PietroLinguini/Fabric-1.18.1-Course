package net.pietrolinguini.mccourse.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModEnchantments {
    val LIGHTNING_STRIKER = register(
        "lightning_striker",
        LightningStrikerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND)
    )

    private fun register(name: String, enchantment: Enchantment): Enchantment {
        return Registry.register(Registry.ENCHANTMENT, Identifier(MCCourseMod.MOD_ID, name), enchantment)
    }

    fun registerModEnchantments() {
        println("Registering ModEnchantments for ${MCCourseMod.MOD_ID}")
    }
}