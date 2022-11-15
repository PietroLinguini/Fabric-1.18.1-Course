package net.pietrolinguini.mccourse.mixins

import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.pietrolinguini.mccourse.util.IEntityDataSaver
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(Entity::class)
abstract class ModEntityDataSaver: IEntityDataSaver {
    private var persistentData: NbtCompound = NbtCompound()

    override fun getPersistentData(): NbtCompound {
        return persistentData
    }

    @Inject(method = ["writeNbt"], at = [At("HEAD")])
    fun injectWriteMethod(nbt: NbtCompound, info: CallbackInfoReturnable<*>) {
        nbt.put("mccourse.kaupen_data", persistentData)
    }

    @Inject(method = ["readNbt"], at = [At("HEAD")])
    fun injectReadMethod(nbt: NbtCompound, info: CallbackInfo) {
        if (nbt.contains("mccourse.kaupen_data", 10)) {
            persistentData = nbt.getCompound("mccourse.kaupen_data")
        }
    }
}