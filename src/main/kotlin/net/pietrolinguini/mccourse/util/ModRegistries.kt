package net.pietrolinguini.mccourse.util

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.ComposterBlock
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.village.TradeOffer
import net.minecraft.village.VillagerProfession
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.block.ModBlocks
import net.pietrolinguini.mccourse.command.ReturnHomeCommand
import net.pietrolinguini.mccourse.command.SetHomeCommand
import net.pietrolinguini.mccourse.entity.ModEntities
import net.pietrolinguini.mccourse.entity.custom.RaccoonEntity
import net.pietrolinguini.mccourse.entity.custom.TigerEntity
import net.pietrolinguini.mccourse.event.ModPlayerEventCopyFrom
import net.pietrolinguini.mccourse.item.ModItems

object ModRegistries {
    fun registerModStuffs() {
        registerFuels()
        registerModComposterChances()
        registerCommands()
        registerEvents()
        registerStrippables()
        registerAttributes()
        registerCustomTrades()
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

    private fun registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.RACCOON, RaccoonEntity.setAttributes())
        FabricDefaultAttributeRegistry.register(ModEntities.TIGER, TigerEntity.setAttributes())
    }

    private fun registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1) { factories ->
            factories.add { entity, random -> TradeOffer(
                ItemStack(Items.EMERALD, 2),
                ItemStack(ModItems.TURNIP, 12),
                6, 1, 0.02f)
            }
        }


        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3) { factories ->
            factories.add { entity, random -> TradeOffer(
                ItemStack(Items.EMERALD, 6),
                ItemStack(ModItems.ORICHALCUM_PAXEL, 1),
                12, 120, 0.08f)
            }
        }
    }
}