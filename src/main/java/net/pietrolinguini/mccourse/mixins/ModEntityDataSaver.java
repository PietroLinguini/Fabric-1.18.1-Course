package net.pietrolinguini.mccourse.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.pietrolinguini.mccourse.util.IEntityDataSaver;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ModEntityDataSaver implements IEntityDataSaver {
    private NbtCompound persistentData;

    @NotNull
    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null)
            persistentData = new NbtCompound();
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> info) {
        if (persistentData != null)
            nbt.put("mccourse.kaupen_data", persistentData);
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("mccourse.kaupen_data", 10))
            persistentData = nbt.getCompound("mccourse.kaupen_data");
    }
}
