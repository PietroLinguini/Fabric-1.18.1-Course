package net.pietrolinguini.mccourse.item.custom

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World
import net.pietrolinguini.mccourse.item.ModArmorMaterial

class ModArmorItem(material: ArmorMaterial?, slot: EquipmentSlot?, settings: Settings?) :
    ArmorItem(material, slot, settings) {
    companion object {
        val MATERIAL_TO_EFFECT_MAP =
            mapOf(ModArmorMaterial.ORICHALCUM to StatusEffectInstance(StatusEffects.HASTE, 60, 1))
    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (world != null) {
            if (entity is PlayerEntity) {
                if (hasFullSuitOfArmorOn(entity)) {
                    evaluateArmorEffects(entity)
                }
            }
        }
    }

    private fun evaluateArmorEffects(player: PlayerEntity) {
        for (entry in MATERIAL_TO_EFFECT_MAP.entries) {
            val armorMaterial = entry.key
            val statusEffect = entry.value

            if (hasCorrectArmorOn(armorMaterial, player)) {
                val hasPlayerEffect = player.hasStatusEffect(statusEffect.effectType)

                if (!hasPlayerEffect) {
                    println("Applying status effect...")
                    player.playSound(SoundEvents.BLOCK_ANCIENT_DEBRIS_BREAK, 1f, 1f)
                    with(statusEffect) {
                        player.addStatusEffect(StatusEffectInstance(effectType, duration, amplifier))
                    }
                }
            }
        }
    }

    private fun hasFullSuitOfArmorOn(player: PlayerEntity): Boolean {
        val boots = player.inventory.getArmorStack(0)
        val leggings = player.inventory.getArmorStack(1)
        val breastplate = player.inventory.getArmorStack(2)
        val helmet = player.inventory.getArmorStack(3)

        return !helmet.isEmpty && !breastplate.isEmpty && !leggings.isEmpty && !boots.isEmpty
    }

    private fun hasCorrectArmorOn(material: ArmorMaterial, player: PlayerEntity): Boolean {
        val boots = player.inventory.getArmorStack(0).item as ArmorItem
        val leggings = player.inventory.getArmorStack(1).item as ArmorItem
        val breastplate = player.inventory.getArmorStack(2).item as ArmorItem
        val helmet = player.inventory.getArmorStack(3).item as ArmorItem

        return helmet.material == material && breastplate.material == material &&
                leggings.material == material && boots.material == material
    }
}