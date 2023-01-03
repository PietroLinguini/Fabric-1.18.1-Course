package net.pietrolinguini.mccourse.world.gen

object ModWorldGen {
    fun generateModWorldGen() {
        ModOreGeneration.generateOres()

        ModTreeGeneration.generateTrees()
        ModFlowerGeneration.generateFlowers()

        ModEntitySpawn.addEntitySpawn()
    }
}