package net.pietrolinguini.mccourse.recipe

import com.google.gson.JsonObject
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.*
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.util.collection.DefaultedList
import net.minecraft.world.World


class OrichalcumBlasterRecipe(private val id_: Identifier, private val output_: ItemStack, private val recipeItems: DefaultedList<Ingredient>): Recipe<SimpleInventory> {

    object Type: RecipeType<OrichalcumBlasterRecipe> {
        const val ID = "orichalcum_blaster"
    }

    object Serializer: RecipeSerializer<OrichalcumBlasterRecipe> {
        // this is the name given in the json file
        const val ID = "orichalcum_blaster"

        override fun read(id: Identifier, json: JsonObject): OrichalcumBlasterRecipe {
            val output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"))

            val ingredients = JsonHelper.getArray(json, "ingredients")
            val inputs = DefaultedList.ofSize(2, Ingredient.EMPTY)

            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromJson(ingredients[i])
            }

            return OrichalcumBlasterRecipe(id, output, inputs)
        }

        override fun read(id: Identifier, buf: PacketByteBuf): OrichalcumBlasterRecipe {
            val inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)

            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromPacket(buf)
            }

            val output = buf.readItemStack()
            return OrichalcumBlasterRecipe(id, output, inputs)
        }

        override fun write(buf: PacketByteBuf, recipe: OrichalcumBlasterRecipe) {
            buf.writeInt(recipe.ingredients.size)

            for (ing in recipe.ingredients) {
                ing.write(buf)
            }

            buf.writeItemStack(recipe.output)
        }
    }

    /**
     * Determines whether there is a valid recipe inside the inventory
     */
    override fun matches(inventory: SimpleInventory, world: World) =
        recipeItems[0].test(inventory.getStack(1)) && recipeItems[1].test(inventory.getStack(2))

    override fun craft(inventory: SimpleInventory?) = output_

    override fun fits(width: Int, height: Int) = true

    override fun getOutput(): ItemStack = output_.copy()

    override fun getId() = id_

    override fun getSerializer(): RecipeSerializer<*> = Serializer

    override fun getType(): RecipeType<*> = Type
}