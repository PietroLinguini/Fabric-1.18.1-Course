package net.pietrolinguini.mccourse.event

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.server.network.ServerPlayerEntity
import net.pietrolinguini.mccourse.util.IEntityDataSaver

class ModPlayerEventCopyFrom: ServerPlayerEvents.CopyFrom {
    override fun copyFromPlayer(oldPlayer: ServerPlayerEntity?, newPlayer: ServerPlayerEntity?, alive: Boolean) {
        val original = oldPlayer as IEntityDataSaver
        val player = newPlayer as IEntityDataSaver

        player.getPersistentData().putIntArray("homepos", original.getPersistentData().getIntArray("homepos"))
    }
}