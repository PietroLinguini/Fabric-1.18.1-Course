package net.pietrolinguini.mccourse.entity.custom

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class RaccoonEntity(entityType: EntityType<out AnimalEntity>, world: World): AnimalEntity(entityType, world), IAnimatable {
    companion object {
        fun setAttributes(): DefaultAttributeContainer.Builder =
            createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
    }

    init {
        ignoreCameraFrustum = true
    }

    private val factory = AnimationFactory(this)

    override fun initGoals() {
        val thisRaccoon = this
        with(goalSelector) {
            add(0, SwimGoal(thisRaccoon))
            add(2, WanderAroundPointOfInterestGoal(thisRaccoon, 0.75, false))
            add(3, WanderAroundFarGoal(thisRaccoon, 0.75, 1f))
            add(4, LookAroundGoal(thisRaccoon))
            add(5, LookAtEntityGoal(thisRaccoon, PlayerEntity::class.java, 8.0f))
        }
    }

    override fun getAmbientSound(): SoundEvent? =
        SoundEvents.ENTITY_DOLPHIN_AMBIENT

    override fun getHurtSound(source: DamageSource?): SoundEvent? =
        SoundEvents.ENTITY_DOLPHIN_HURT

    override fun getDeathSound(): SoundEvent? =
        SoundEvents.ENTITY_PIG_DEATH

    override fun playStepSound(pos: BlockPos?, state: BlockState?) {
        playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1f)
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? = null

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller",
            0f, this::predicate))
    }

    override fun getFactory() = factory

    // ANIMATIONS
    private fun <E: IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(
            AnimationBuilder().addAnimation("animation.raccoon.${if (event.isMoving) "walk" else "idle"}", true)
        )

        return PlayState.CONTINUE
    }
}