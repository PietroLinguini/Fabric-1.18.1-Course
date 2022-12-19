package net.pietrolinguini.mccourse.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// GPL-3.0 License: https://github.com/nyuppo/fabric-sign-example by nyuppo
@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (this.equals(BlockEntityType.SIGN) &&
                (state.getBlock() instanceof SignBlock || state.getBlock() instanceof WallSignBlock))
            info.setReturnValue(true);
    }
}
