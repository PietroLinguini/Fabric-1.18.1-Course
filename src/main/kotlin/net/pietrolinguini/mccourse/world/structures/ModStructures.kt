package net.pietrolinguini.mccourse.world.structures

import net.minecraft.world.gen.GenerationStep
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.mixins.StructureFeatureAccessor

object ModStructures {
    /**
     * Registers the structures itself and sets what its path is. In this case, the
     * structure will have the Identifier of mccourse:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registers. It's great for mod/datapacks compatibility.
     */
    val SKY_STRUCTURES = SkyStructures()

    fun registerSkyStructureFeatures() {
        // The generation step for when to generate the structure. There are 10 stages you can pick from!
        // This surface structure stage places the structure before plants and ores are generated.
        StructureFeatureAccessor.callRegister(MCCourseMod.MOD_ID + ":sky_structures",
            SKY_STRUCTURES, GenerationStep.Feature.SURFACE_STRUCTURES)
    }
}