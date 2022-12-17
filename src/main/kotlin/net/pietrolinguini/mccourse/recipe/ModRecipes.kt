package net.pietrolinguini.mccourse.recipe

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.pietrolinguini.mccourse.MCCourseMod

object ModRecipes {
    fun register() {
        Registry.register(
            Registry.RECIPE_SERIALIZER, Identifier(MCCourseMod.MOD_ID, OrichalcumBlasterRecipe.Serializer.ID),
            OrichalcumBlasterRecipe.Serializer
        )
        Registry.register(
            Registry.RECIPE_TYPE, Identifier(MCCourseMod.MOD_ID, OrichalcumBlasterRecipe.Type.ID),
            OrichalcumBlasterRecipe.Type
        )
    }
}