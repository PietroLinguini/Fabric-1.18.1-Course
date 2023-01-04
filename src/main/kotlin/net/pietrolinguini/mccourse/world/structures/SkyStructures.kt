package net.pietrolinguini.mccourse.world.structures

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.structure.PoolStructurePiece
import net.minecraft.structure.PostPlacementProcessor
import net.minecraft.structure.StructureGeneratorFactory
import net.minecraft.structure.StructurePiecesGenerator
import net.minecraft.structure.StructureSetKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig
import net.pietrolinguini.mccourse.MCCourseMod
import org.apache.logging.log4j.Level
import java.util.Optional

class SkyStructures: StructureFeature<StructurePoolFeatureConfig>(CODEC, SkyStructures::createPiecesGenerator, PostPlacementProcessor.EMPTY) {
    companion object {
        // A custom codec that changes the size limit for our code_structure_sky_fan.json's config to not be capped at 7.
        // With this, we can have a structure with a size limit up to 30 if we want to have extremely long branches of pieces in the structure.
        val CODEC: Codec<StructurePoolFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(StructurePoolFeatureConfig::getStartPool),
                Codec.intRange(0, 30).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
            )
                .apply(instance, ::StructurePoolFeatureConfig)
        }

        /*
         * This is where extra checks can be done to determine if the structure can spawn here.
         * This only needs to be overridden if you're adding additional spawn conditions.
         *
         * Fun fact, if you set your structure separation/spacing to be 0/1, you can use
         * isFeatureChunk to return true only if certain chunk coordinates are passed in
         * which allows you to spawn structures only at certain coordinates in the world.
         *
         * Basically, this method is used for determining if the land is at a suitable height,
         * if certain other structures are too close or not, or some other restrictive condition.
         *
         * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
         * (Bedrock Edition seems to not have the same check)
         *
         * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
         * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
         * Then loop through it and look for land with air above it and set blockpos's Y value to it.
         * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
         * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
         *
         * Also, please for the love of god, do not do dimension checking here.
         * If you do and another mod's dimension is trying to spawn your structure,
         * the locate command will make minecraft hang forever and break the game.
         * Use the biome tags for where to spawn the structure and users can datapack
         * it to spawn in specific biomes that aren't in the dimension they don't like if they wish.
         */
        private fun isFeatureChunk(context: StructureGeneratorFactory.Context<StructurePoolFeatureConfig>): Boolean {
            // Grabs the chunk we are at
            val chunkPos = context.chunkPos

            // Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
            // to demonstrate how this method is good for checking extra conditions for spawing
            return !context.chunkGenerator.method_41053(StructureSetKeys.OCEAN_MONUMENTS, context.seed, chunkPos.x, chunkPos.z, 10)
        }

        fun createPiecesGenerator(context: StructureGeneratorFactory.Context<StructurePoolFeatureConfig>): Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> {
            // Check if the spot is valid for our structure. This is just as another method for cleanness.
            // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
            if (!isFeatureChunk(context))
                return Optional.empty()

            // Turns the chunk coordinates into actual coordinates we can use. (Gets the center of that chunk)
            var blockPos = context.chunkPos.getCenterAtY(0)

            // Set's our spawning blockPos's y offset to be 60 blocks up.
            // Since we are going to have heightmap/terrain height spawning set to true further down, this will make it so we spawn 60 blocks above terrrain
            // If we wanted to spawn on ocean floor, we would set heighmap/terrain height spawning to false and grab the y value of the terrain with OCEAN_FLOOR_WG heightmap.
            blockPos = blockPos.up(60)

            val structurePiecesGenerator = StructurePoolBasedGenerator.generate(
                context, // Used for JigsawPlacement to get all the proper behaviors done.
                ::PoolStructurePiece, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                blockPos, // Position of the structure. Y value is ignored if last paramter is set to true
                false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting
                // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                true // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
                // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
                // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
                // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
            )

            /*
             * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
             * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
             * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
             *
             * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
             * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18.2/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
             */
            if (structurePiecesGenerator.isPresent)
                // I use to debug and quickly find out if the structure is spawning or not and where it is.
                // This returning the coordinates of the center starting piece.
                MCCourseMod.LOGGER.log(Level.DEBUG, "Sky Structure at {}", blockPos)

            // return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces
            return structurePiecesGenerator
        }
    }
}