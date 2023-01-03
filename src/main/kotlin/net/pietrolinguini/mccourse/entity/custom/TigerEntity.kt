package net.pietrolinguini.mccourse.entity.custom

import net.minecraft.entity.Dismounting
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.Mount
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Arm
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.pietrolinguini.mccourse.item.ModItems
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class TigerEntity(entityType: EntityType<out TameableEntity>, world: World): TameableEntity(entityType, world), Mount, IAnimatable {
    companion object {
        val SITTING: TrackedData<Boolean> =
            DataTracker.registerData(TigerEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        fun setAttributes(): DefaultAttributeContainer.Builder =
            createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
    }

    private val factory = AnimationFactory(this)

    private fun <E: IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(
            AnimationBuilder().addAnimation("animation.tiger.${
                if (event.isMoving) "walk" else if (this.isSitting) "sitting" else "idle"
            }", true)
        )

        return PlayState.CONTINUE
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, SitGoal(this)) // Important that the Sit Goal is higher than the WanderGoal!!
        targetSelector.add(2, TrackOwnerAttackerGoal(this))
        goalSelector.add(2, WanderAroundPointOfInterestGoal(this, 0.75, false))
        goalSelector.add(3, WanderAroundFarGoal(this, 0.75, 1.0f))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? = null

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller", 0f, ::predicate))
    }

    override fun getFactory() = factory

    // TAMABLE
    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        val item = itemStack.item
        val tamableItem = ModItems.TURNIP

        if (world.isClient) {
            val flag = isOwner(player) || isTamed || item == tamableItem && !isTamed
            return if (flag) ActionResult.CONSUME else ActionResult.PASS
        } else {
            if (isTamed) {
                if (player.isSneaking && hand == Hand.MAIN_HAND)
                    isSitting = !isSitting

                if (isBreedingItem(itemStack) && health < maxHealth) {
                    if (!player.abilities.creativeMode)
                        itemStack.decrement(1)

                    item.foodComponent?.hunger?.toFloat()?.let { heal(it) }
                    return ActionResult.SUCCESS
                }
                player.startRiding(this)
            } else if (item == tamableItem && !isOnFire) {
                if (!player.abilities.creativeMode)
                    itemStack.decrement(1)

                if (random.nextInt(3) == 0) {
                    setOwner(player)
                    navigation.recalculatePath()
                    target = null
                    world.sendEntityStatus(this, 7.toByte())
                    isSitting = true
                } else
                    world.sendEntityStatus(this, 6.toByte())

                return ActionResult.SUCCESS
            }

            return super.interactMob(player, hand)
        }
    }

    override fun setSitting(sitting: Boolean) {
        dataTracker.set(SITTING, sitting)
        super.setSitting(sitting)
    }

    override fun isSitting(): Boolean = dataTracker.get(SITTING)

    override fun setTamed(tamed: Boolean) {
        super.setTamed(tamed)

        val maxHealth = if (tamed) 60.0 else 30.0
        val attackDamage = if (tamed) 4.0 else 2.0
        val moveSpeed = if (tamed) 0.35 else 0.25

        getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = maxHealth
        getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)?.baseValue = attackDamage
        getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = moveSpeed
    }

    override fun canBeLeashedBy(player: PlayerEntity) = false

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(SITTING, false)
    }

    // RIDEABLE
    override fun canBeControlledByRider() = primaryPassenger is LivingEntity

    override fun getPrimaryPassenger() = firstPassenger

    override fun travel(movementInput: Vec3d) {
        if (!isAlive) return

        if (!(hasPassengers() && canBeControlledByRider())) {
            airStrafingSpeed = 0.02f
            super.travel(movementInput)
            return
        }

        val livingEntity = primaryPassenger as LivingEntity
        yaw = livingEntity.yaw
        prevYaw = yaw
        pitch = livingEntity.pitch * 0.05f
        setRotation(yaw, pitch)
        headYaw = bodyYaw * 0.5f
        val f = livingEntity.sidewaysSpeed * 0.5f
        var g = livingEntity.forwardSpeed
        if (g <= 0f) {
            g *= 0.25f
        }

        if (isLogicalSideForUpdatingMovement) {
            movementSpeed = getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED).toFloat()
            super.travel(Vec3d(f.toDouble(), movementInput.y, g.toDouble()))
        } else if (livingEntity is PlayerEntity) {
            velocity = Vec3d.ZERO
        }

        updateLimbs(this, false)
        tryCheckBlockCollision()
    }

    override fun updatePassengerForDismount(passenger: LivingEntity): Vec3d {
        val vec3d = getPassengerDismountOffset(this.width.toDouble(), passenger.width.toDouble(),
            this.yaw + if (passenger.mainArm == Arm.RIGHT) 90f else -90f)
        val vec3d2 = locateSafeDismountingPos(vec3d, passenger)
        if (vec3d2 != null)
            return vec3d2
        val vec3d3 = getPassengerDismountOffset(this.width.toDouble(), passenger.width.toDouble(),
            this.yaw + if (passenger.mainArm == Arm.LEFT) 90f else -90f)
        val vec3d4 = locateSafeDismountingPos(vec3d3, passenger)
        if (vec3d4 != null)
            return vec3d4
        return this.pos
    }

    private fun locateSafeDismountingPos(offset: Vec3d, passenger: LivingEntity): Vec3d? {
        val d = x + offset.x
        val e = boundingBox.minY
        val f = z + offset.z
        val mutable = BlockPos.Mutable()

        block0@for (entityPose in passenger.poses) {
            mutable.set(d, e, f)
            val g = boundingBox.maxY + 0.75
            do {
                var vec3d: Vec3d
                var box: Box
                val h = world.getDismountHeight(mutable)
                if (mutable.y.toDouble() + h > g) continue@block0
                box = passenger.getBoundingBox(entityPose)
                vec3d = Vec3d(d, mutable.y.toDouble() + h, f)
                if (Dismounting.canDismountInBlock(h)
                    && Dismounting.canPlaceEntityAt(world, passenger, box.offset(vec3d))) {
                    passenger.pose = entityPose
                    return vec3d
                }
                mutable.move(Direction.UP)
            } while (mutable.y.toDouble() < g)
        }
        return null
    }
}
