package wardentools.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.entity.custom.NoctilureEntity;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method="aiStep", at=@At("HEAD"))
    private void onAiStep(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (player.getVehicle() instanceof NoctilureEntity noctilure) {
            if (player.input.jumping) {
                noctilure.onPlayerJump(player);
            }
        }
    }
}
