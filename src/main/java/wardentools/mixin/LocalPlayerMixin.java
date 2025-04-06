package wardentools.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.entity.custom.NoctilureEntity;
import wardentools.gui.windscreen.WindJournalScreen;
import wardentools.items.ItemRegistry;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Shadow @Final protected Minecraft minecraft;

    @Inject(method="aiStep", at=@At("HEAD"))
    private void onAiStep(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (player.getVehicle() instanceof NoctilureEntity noctilure) {
            if (player.input.jumping) {
                noctilure.onPlayerJump(player);
            }
        }
    }

    @Inject(method="openItemGui", at=@At("HEAD"))
    private void onOpenItemGui(ItemStack stack, InteractionHand hand, CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (stack.is(ItemRegistry.WIND_JOURNAL.get())) {
            this.minecraft.setScreen(new WindJournalScreen());
        }
    }
}
