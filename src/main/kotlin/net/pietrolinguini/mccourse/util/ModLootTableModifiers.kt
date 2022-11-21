package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.item.ModItems

object ModLootTableModifiers {
    val GRASS_BLOCK_ID = Identifier("minecraft", "blocks/grass")
    val IGLOO_STRUCTURE_CHEST_ID = Identifier("minecraft", "chests/igloo_chest")
    val CREEPER_ID = Identifier("minecraft", "entities/creeper")

    fun modifyLootTables() {
        LootTableLoadingCallback.EVENT.register(({ _, _, id, supplier, _ ->
            //check for leaves loot table
            if (GRASS_BLOCK_ID == id) {
                // Adds turnip Seeds to the grass loot table
                val poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
                    .with(ItemEntry.builder(ModItems.TURNIP_SEEDS))
                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build())
                supplier.withPool(poolBuilder.build())
            }

            if (IGLOO_STRUCTURE_CHEST_ID == id) {
                // Adds a Dowsing Rod into the Igloo Chest with 75% chance.
                val poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                    .with(ItemEntry.builder(ModItems.DOWSING_ROD))
                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                supplier.withPool(poolBuilder.build())
            }

            if (CREEPER_ID == id) {
                // Adds a Coal Sliver into the creeper death loot table
                val poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1f))
                    .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                    .with(ItemEntry.builder(ModItems.COAL_SLIVER))
                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                supplier.withPool(poolBuilder.build())
            }
        }))
    }
}