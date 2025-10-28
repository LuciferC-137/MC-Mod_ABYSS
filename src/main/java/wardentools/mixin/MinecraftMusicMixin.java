package wardentools.mixin;

import net.minecraft.Optionull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wardentools.worldgen.dimension.ModDimensions;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMusicMixin {

    @Shadow public Screen screen;
    @Shadow @Nullable public LocalPlayer player;
    @Final @Shadow public Gui gui;
    @Final @Shadow private MusicManager musicManager;

    @Inject(method="getSituationalMusic", at=@At("HEAD"), cancellable = true)
    public void overrideGetSituationalMusic(CallbackInfoReturnable<Music> cir) {
        Music music = Optionull.map(this.screen, Screen::getBackgroundMusic);
        if (music != null) {
            cir.setReturnValue(music);
            cir.cancel();
        } else if (this.player != null) {
            Holder<Biome> holder = this.player.level().getBiome(this.player.blockPosition());
            if (this.player.level().dimension() == Level.END) {
                cir.setReturnValue(this.gui.getBossOverlay().shouldPlayMusic() ? Musics.END_BOSS : Musics.END);
            }
            else if (this.player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
                cir.setReturnValue(holder.value().getBackgroundMusic().orElse(Musics.GAME));
            } else {
                if (!this.musicManager.isPlayingMusic(Musics.UNDER_WATER)
                        && (!this.player.isUnderWater()
                        || !holder.is(BiomeTags.PLAYS_UNDERWATER_MUSIC))) {
                    cir.setReturnValue(this.player.level().dimension() != Level.NETHER
                            && this.player.getAbilities().instabuild
                            && this.player.getAbilities().mayfly ?
                            Musics.CREATIVE : holder.value().getBackgroundMusic().orElse(Musics.GAME));
                } else {
                    cir.setReturnValue(Musics.UNDER_WATER);
                }
            }
            cir.cancel();
        }
        // Here we are in the main menu
    }
}
