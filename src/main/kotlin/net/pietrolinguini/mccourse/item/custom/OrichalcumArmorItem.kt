package net.pietrolinguini.mccourse.item.custom

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.pietrolinguini.mccourse.item.ModItems
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class OrichalcumArmorItem(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings) :
    ModArmorItem(material, slot, settings), IAnimatable {
    private val factory = AnimationFactory(this)

    // Predicate runs every frame
    private fun <P : IAnimatable> predicate(event: AnimationEvent<P>): PlayState {
        // This is all the extra data this event carries. The livingEntity is the entity
        // that's wearing the armor. The itemStack and equipmentSlotType are self-explanatory.
        val livingEntity = event.getExtraDataOfType(LivingEntity::class.java)[0]

        // Always loop the animation but later on in this method we'll decide whether to actually play it
        event.controller.setAnimation(AnimationBuilder().addAnimation("idle", true))

        // If the living entity is an ArmorStand just play the animation nonstop
        if (livingEntity is ArmorStandEntity)
            return PlayState.CONTINUE

        // The entity is a player, so we want to play if the player is wearing the full set of armor
        else if (livingEntity is PlayerEntity) {
            // Get all the equipment, aka the armor, currently held item, and offhand item
            val equipmentList = mutableListOf<Item>()
            livingEntity.itemsEquipped.forEach { equipmentList.add(it.item) }

            // elements 2 to 6 are the armor, so we take the sublist. armorList now only contains
            // the 4 armor slots
            val armorList = equipmentList.subList(2, 6)

            // Make sure the player is wearing all the armor. If they are, continue playing
            // the animation, otherwise stop
            val isWearingAll = armorList.containsAll(
                listOf(
                    ModItems.ORICHALCUM_BOOTS, ModItems.ORICHALCUM_LEGGINGS,
                    ModItems.ORICHALCUM_CHESTPLATE, ModItems.ORICHALCUM_HELMET
                )
            )
            return if (isWearingAll) PlayState.CONTINUE else PlayState.STOP
        }

        return PlayState.STOP
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this,
            "controller", 20f, ::predicate))
    }

    override fun getFactory() = factory
}