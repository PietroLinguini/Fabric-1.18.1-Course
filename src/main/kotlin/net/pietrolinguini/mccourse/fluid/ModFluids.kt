package net.pietrolinguini.mccourse.fluid

import net.minecraft.fluid.FlowableFluid
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModFluids {

    val HONEY_STILL = register(
        "honey_still",
        HoneyFluid.Still()
    )

    val HONEY_FLOWING = register(
        "honey_flowing",
        HoneyFluid.Flowing()
    )

    fun register(name: String, fluid: FlowableFluid): FlowableFluid {
        return Registry.register(Registry.FLUID, Identifier(MCCourseMod.MOD_ID, name), fluid)
    }
}