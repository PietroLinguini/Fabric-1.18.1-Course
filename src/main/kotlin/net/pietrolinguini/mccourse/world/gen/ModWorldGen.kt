package net.pietrolinguini.mccourse.world.gen

object ModWorldGen {
    fun generateModWorldGen() {
        ModTreeGeneration.generateTrees()
        ModFlowerGeneration.generateFlowers()
    }
}