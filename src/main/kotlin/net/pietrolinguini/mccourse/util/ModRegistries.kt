package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.ComposterBlock
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.command.ReturnHomeCommand
import net.pietrolinguini.mccourse.command.SetHomeCommand
import net.pietrolinguini.mccourse.event.ModPlayerEventCopyFrom
import net.pietrolinguini.mccourse.item.ModItems

object ModRegistries {
    fun registerModStuffs() {
        registerFuels()
        registerModComposterChances()
        registerCommands()
        registerEvents()
        registerStrippables()
    }

    private fun registerFuels() {
        println("Registering Fuels for ${MCCourseMod.MOD_ID}")
        val registry = FuelRegistry.INSTANCE

        // 400 / 20 = 20 Seconds
        registry.add(ModItems.COAL_SLIVER, 400)
    }

    private fun registerModComposterChances() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP_SEEDS, 0.35f)
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TURNIP, 0.65f)
    }

    private fun registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register)
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register)
    }

    private fun registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(ModPlayerEventCopyFrom())
    }

    private fun registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.CHERRY_BLOSSOM_LOG, ModBlocks.STRIPPED_CHERRY_BLOSSOM_LOG)
        StrippableBlockRegistry.register(ModBlocks.CHERRY_BLOSSOM_WOOD, ModBlocks.STRIPPED_CHERRY_BLOSSOM_WOOD)
    }
}