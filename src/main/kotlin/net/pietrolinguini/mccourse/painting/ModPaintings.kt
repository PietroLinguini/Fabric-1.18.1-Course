package net.pietrolinguini.mccourse.painting

import net.minecraft.entity.decoration.painting.PaintingMotive
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModPaintings {
    val SUNSET = registerPainting("sunset", PaintingMotive(32, 16))
    val PLANT = registerPainting("plant", PaintingMotive(16, 16))
    val WANDERER = registerPainting("wanderer", PaintingMotive(16, 32))

    private fun registerPainting(name: String, paintingMotive: PaintingMotive): PaintingMotive {
        return Registry.register(Registry.PAINTING_MOTIVE, Identifier(MCCourseMod.MOD_ID, name), paintingMotive)
    }

    fun registerPaintings() {
        println("Registering Paintings for ${MCCourseMod.MOD_ID}")
    }
}