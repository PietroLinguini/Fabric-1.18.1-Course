package net.pietrolinguini.mccourse.util

import net.minecraft.nbt.NbtCompound

interface IEntityDataSaver {
    fun getPersistentData(): NbtCompound
}