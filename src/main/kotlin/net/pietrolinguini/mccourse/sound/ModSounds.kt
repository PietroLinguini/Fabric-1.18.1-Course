package net.pietrolinguini.mccourse.sound

import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModSounds {
    val DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore")

    private fun registerSoundEvent(name: String): SoundEvent {
        val id = Identifier(MCCourseMod.MOD_ID, name)
        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent(id))
    }
}