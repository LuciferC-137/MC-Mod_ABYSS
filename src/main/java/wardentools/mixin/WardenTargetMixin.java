package wardentools.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wardentools.entity.interfaces.CorruptionMonster;

@Mixin(Warden.class)
public abstract class WardenTargetMixin {

    @Inject(method = "canTargetEntity", at = @At("HEAD"), cancellable = true)
    private void preventTargetingContagionMonster(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof CorruptionMonster) {
            cir.setReturnValue(false);
        }
    }
}
