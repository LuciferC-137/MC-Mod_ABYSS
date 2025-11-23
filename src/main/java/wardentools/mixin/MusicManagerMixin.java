package wardentools.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.sounds.music.AbyssMusicManager;
import wardentools.sounds.music.AbyssMusicManagerAccessor;
import wardentools.worldgen.dimension.ModDimensions;

@Mixin(MusicManager.class)
public abstract class MusicManagerMixin implements AbyssMusicManagerAccessor {
    @Final @Shadow private Minecraft minecraft;

    @Unique
    private AbyssMusicManager abyssMusic$customManager;

    @Override
    public AbyssMusicManager abyssMusic$getCustomManager() {
        return this.abyssMusic$customManager;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Minecraft minecraft, CallbackInfo ci) {
        this.abyssMusic$customManager = new AbyssMusicManager(minecraft);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo ci) {
        // Vérifier si le joueur est dans la dimension Abyss
        boolean isInAbyss = this.minecraft.player != null
                && this.minecraft.player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY;

        if (isInAbyss) {
            // Utiliser le gestionnaire personnalisé pour l'Abyss
            this.abyssMusic$customManager.tick();
            ci.cancel();
            return;
        }
    }
}
