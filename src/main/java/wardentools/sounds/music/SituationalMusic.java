package wardentools.sounds.music;

import net.minecraft.Optionull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.sounds.ModMusics;

@OnlyIn(Dist.CLIENT)
public class SituationalMusic {
    private boolean isInIncarnationFight = false;
    private final Minecraft minecraft;

    public SituationalMusic(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public Music getAbyssSituationalMusic(AbyssMusicManager abyssMusicManager) {
        Music music = Optionull.map(minecraft.screen, Screen::getBackgroundMusic);
        if (music != null) {
            // If a screen has specific background music, use it
            return music;
        } else if (minecraft.player != null) {
            if (isInIncarnationFight) {
                return ModMusics.INCARNATION_THEME;
            } else {
                Holder<Biome> biome = minecraft.player.level().getBiome(minecraft.player.blockPosition());
                return (biome.value()).getBackgroundMusic().orElse(Musics.GAME);
            }
        } else {
            // If the player does not exist, fallback to menu music.
            // This should not happen as the abyss music manager is not intended to be used outside the Abyss dimension.
            return Musics.MENU;
        }
    }

    public void setInIncarnationFight(boolean inFight) {
        this.isInIncarnationFight = inFight;
    }
}
