package net.pietrolinguini.mccourse.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import java.util.function.Supplier

enum class ModArmorMaterial(
    private var materialName: String,
    private var durabilityMultiplier: Int,
    private var protectionAmounts: IntArray,
    private var enchantability: Int,
    private var equipSound: SoundEvent,
    private var toughness: Float,
    private var knockbackResistance: Float,
    private var repairIngredientSupplier: Supplier<*>
): ArmorMaterial {
    ORICHALCUM(
        "orichalcum",
        18,
        intArrayOf(3, 5, 7, 2),
        25,
        SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
        0.0f,
        0.0f,
        {

            Ingredient.ofItems(ModItems.ORICHALCUM_INGOT)
        }
    );

    companion object {
        val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    }

    override fun getDurability(slot: EquipmentSlot?): Int {
        return if (slot != null) BASE_DURABILITY[slot.entitySlotId] * durabilityMultiplier else - 1
    }

    override fun getProtectionAmount(slot: EquipmentSlot?): Int {
        return if (slot != null) protectionAmounts[slot.entitySlotId] else -1
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return equipSound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredientSupplier.get() as Ingredient
    }

    override fun getName(): String {
        return materialName
    }

    override fun getToughness(): Float {
        return toughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }
}