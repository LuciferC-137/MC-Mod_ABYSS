package wardentools.items.clientutils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.effect.ModEffects;

@OnlyIn(Dist.CLIENT)
public class WardenHeartFoilManager {

    public static boolean isFoil() {
        Player player = Minecraft.getInstance().player;
        if (player == null) return false;
        return player.getEffect(ModEffects.CORRUPTION_VESSEL) != null;
    }

}
