package net.pietrolinguini.mccourse.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

class LightningStrikerEnchantment(weight: Rarity?, type: EnchantmentTarget?, vararg slotTypes: EquipmentSlot) :
    Enchantment(weight, type, slotTypes) {

    override fun onTargetDamaged(user: LivingEntity?, target: Entity?, level: Int) {
        if (user?.world != null && !user.world.isClient) {
            val world = user.world as ServerWorld
            val player = user as PlayerEntity
            val position = target?.blockPos

            for (i in 0..level) {
                EntityType.LIGHTNING_BOLT.spawn(
                    /* world = */ world,
                    /* itemNbt = */ null,
                    /* name = */ null,
                    /* player = */ player,
                    /* pos = */ position,
                    /* spawnReason = */ SpawnReason.TRIGGERED,
                    /* alignPosition = */ true,
                    /* invertY = */ true
                )
            }

        }
    }

    override fun getMaxLevel(): Int {
        return 2
    }
}