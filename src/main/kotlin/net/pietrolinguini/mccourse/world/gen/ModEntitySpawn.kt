package net.pietrolinguini.mccourse.world.gen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.mixin.`object`.builder.SpawnRestrictionAccessor
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.pietrolinguini.mccourse.entity.ModEntities

object ModEntitySpawn {
    fun addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.PLAINS), SpawnGroup.CREATURE,
            ModEntities.RACCOON, 25, 2, 5)
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.JUNGLE), SpawnGroup.CREATURE,
            ModEntities.TIGER, 35, 2, 3)

        SpawnRestrictionAccessor.callRegister(ModEntities.RACCOON, SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
        SpawnRestrictionAccessor.callRegister(ModEntities.TIGER, SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
    }
}
