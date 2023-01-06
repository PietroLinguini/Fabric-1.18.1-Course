package net.pietrolinguini.mccourse.villager

import com.google.common.collect.ImmutableSet
import net.fabricmc.fabric.mixin.`object`.builder.PointOfInterestTypeAccessor
import net.fabricmc.fabric.mixin.`object`.builder.VillagerProfessionAccessor
import net.minecraft.block.Block
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks

object ModVillagers {
    val BLASTER_POI = registerPOI("blasterpoi", ModBlocks.ORICHALCUM_BLASTER)
    val BLAST_MASTER = registerProfession("blastmaster", BLASTER_POI)

    fun registerProfession(name: String, type: PointOfInterestType): VillagerProfession =
        Registry.register(Registry.VILLAGER_PROFESSION, Identifier(MCCourseMod.MOD_ID, name),
            VillagerProfessionAccessor.create(name, type, ImmutableSet.of(), ImmutableSet.of(),
                SoundEvents.ENTITY_VILLAGER_WORK_ARMORER))

    fun registerPOI(name: String, block: Block): PointOfInterestType =
        Registry.register(Registry.POINT_OF_INTEREST_TYPE, Identifier(MCCourseMod.MOD_ID, name),
            PointOfInterestTypeAccessor.callCreate(name,
                ImmutableSet.copyOf(block.stateManager.states), 1, 1))

    fun setupPOIS() {
        PointOfInterestTypeAccessor.callSetup(BLASTER_POI)
    }
}