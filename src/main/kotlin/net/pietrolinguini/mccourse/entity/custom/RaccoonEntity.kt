package net.pietrolinguini.mccourse.entity.custom

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.pietrolinguini.mccourse.entity.variants.RaccoonVariant
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class RaccoonEntity(entityType: EntityType<out TameableEntity>, world: World): TameableEntity(entityType, world), IAnimatable {
    companion object {
        val TYPE_VARIANT_ID: TrackedData<Int> =
            DataTracker.registerData(RaccoonEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val SITTING: TrackedData<Boolean> =
            DataTracker.registerData(RaccoonEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

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
            add(1, SitGoal(thisRaccoon)) // Important that the Sit Goal is higher than WanderGoal!
            add(2, TrackOwnerAttackerGoal(thisRaccoon))
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
            AnimationBuilder().addAnimation("animation.raccoon.${if (event.isMoving) "walk" else if (this.isSitting) "sitting" else "idle"}", true)
        )

        return PlayState.CONTINUE
    }

    // TAMABLE
    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        val itemForTaming = Items.APPLE

        // If you're trying to tame the raccoon
        if (itemStack.item == itemForTaming && !isTamed) {
            if (world.isClient)
                return ActionResult.CONSUME
            else {
                if (!player.abilities.creativeMode)
                    itemStack.decrement(1)
                if (!world.isClient) {
                    setOwner(player)
                    navigation.recalculatePath()
                    target = null
                    world.sendEntityStatus(this, 7.toByte())
                    setSit(true)
                }

                return ActionResult.SUCCESS
            }
        }

        // If the raccoon is already tamed and right-clicking it
        if (isTamed && !world.isClient && hand == Hand.MAIN_HAND) {
            setSit(!isSitting)
            return ActionResult.SUCCESS
        }

        // If you have an apple in hand
        if (itemStack.item == itemForTaming)
            return ActionResult.PASS

        return super.interactMob(player, hand)
    }

    fun setSit(sitting: Boolean) {
        dataTracker.set(SITTING, sitting)
        isSitting = sitting
    }

    override fun isSitting(): Boolean = dataTracker.get(SITTING)

    override fun setTamed(tamed: Boolean) {
        super.setTamed(tamed)

        val maxHealth = if (tamed) 60.0 else 30.0
        val attackDamage = if (tamed) 4.0 else 2.0
        val moveSpeed = if (tamed) 0.5 else 0.25

        getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = maxHealth
        getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)?.baseValue = attackDamage
        getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = moveSpeed
    }

    override fun canBeLeashedBy(player: PlayerEntity) = false

    // VARIANTS
    override fun initialize(
        world: ServerWorldAccess?,
        difficulty: LocalDifficulty?,
        spawnReason: SpawnReason?,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        val variant = Util.getRandom(RaccoonVariant.values(), random)
        setVariant(variant)
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    fun getVariant() = RaccoonVariant.byId(getTypeVariant() and 255)

    private fun getTypeVariant(): Int = dataTracker.get(TYPE_VARIANT_ID)

    private fun setVariant(variant: RaccoonVariant) {
        dataTracker.set(TYPE_VARIANT_ID, variant.id and 255)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Variant", getTypeVariant())
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        dataTracker.set(TYPE_VARIANT_ID, nbt.getInt("Variant"))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(SITTING, false)
        dataTracker.startTracking(TYPE_VARIANT_ID, 0)
    }
}