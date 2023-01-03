package net.pietrolinguini.mccourse.entity

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import net.pietrolinguini.mccourse.entity.custom.TigerEntity

object ModEntities {
    val RACCOON: EntityType<RaccoonEntity> = Registry.register(
        Registry.ENTITY_TYPE, Identifier(MCCourseMod.MOD_ID, "raccoon"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ::RaccoonEntity)
            .dimensions(EntityDimensions.fixed(0.4f, 0.3f)).build())

    val TIGER: EntityType<TigerEntity> = Registry.register(
        Registry.ENTITY_TYPE, Identifier(MCCourseMod.MOD_ID, "tiger"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ::TigerEntity)
            .dimensions(EntityDimensions.fixed(1f, 0.75f)).build()
    )
}