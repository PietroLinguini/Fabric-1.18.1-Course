package net.pietrolinguini.mccourse.sound

import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModSounds {
    val DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore")

    val ORICHALCUM_LAMP_BREAK = registerSoundEvent("orichalcum_lamp_break")
    val ORICHALCUM_LAMP_STEP = registerSoundEvent("orichalcum_lamp_step")
    val ORICHALCUM_LAMP_PLACE = registerSoundEvent("orichalcum_lamp_place")
    val ORICHALCUM_LAMP_HIT = registerSoundEvent("orichalcum_lamp_hit")
    val ORICHALCUM_LAMP_FALL = registerSoundEvent("orichalcum_lamp_fall")

    val ORICHALCUM_SOUNDS = BlockSoundGroup(
        1f,
        1f,
        ORICHALCUM_LAMP_BREAK,
        ORICHALCUM_LAMP_STEP,
        ORICHALCUM_LAMP_PLACE,
        ORICHALCUM_LAMP_HIT,
        ORICHALCUM_LAMP_FALL
    )

    private fun registerSoundEvent(name: String): SoundEvent {
        val id = Identifier(MCCourseMod.MOD_ID, name)
        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent(id))
    }
}