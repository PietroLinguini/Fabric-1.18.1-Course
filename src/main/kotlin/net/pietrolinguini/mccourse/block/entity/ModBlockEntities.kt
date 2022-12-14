package net.pietrolinguini.mccourse.block.entity

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks

object ModBlockEntities {
    val ORICHALCUM_BLASTER = register(
        "orichalcum_blaster", ::OrichalcumBlasterEntity, ModBlocks.ORICHALCUM_BLASTER
    )

    private fun <B: BlockEntity> register(name: String, blockEntity:  FabricBlockEntityTypeBuilder.Factory<B>, block: Block): BlockEntityType<B> {
        return Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            Identifier(MCCourseMod.MOD_ID, name),
            FabricBlockEntityTypeBuilder.create(blockEntity, block).build(null)
        )
    }

    fun registerAllEntities() {
        println("Registering ModBlockEntities for ${MCCourseMod.MOD_ID}")
    }

}