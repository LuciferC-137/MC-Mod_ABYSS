package wardentools.misc.wind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class WhisperManager {
    public static final WhisperManager INSTANCE = new WhisperManager();
    private static final WindWhispers WHISPERS = new WindWhispers();
    private static final int MIN_TIME_BETWEEN_WHISPERS = 500;
    private int nextMinTime = MIN_TIME_BETWEEN_WHISPERS;
    private int timeSinceLastWhisper = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        INSTANCE.tick();
    }

    public void tick() {
        timeSinceLastWhisper++;
    }

    public boolean sendRandomWhisperToAllPlayers(ServerLevel level) {
        if (level.dimension() != ModDimensions.ABYSS_LEVEL_KEY) return false;
        if (this.timeSinceLastWhisper > nextMinTime) {
            this.timeSinceLastWhisper = 0;
            this.nextMinTime = MIN_TIME_BETWEEN_WHISPERS + level.random.nextInt(MIN_TIME_BETWEEN_WHISPERS);
            level.players().forEach(this::sendRandomWhisperToPlayer);
            return true;
        }
        return false;
    }

    public void sendRandomWhisperToPlayer(Player player) {
        Minecraft minecraft = Minecraft.getInstance();
        LanguageManager languageManager = minecraft.getLanguageManager();
        String currentLanguage = languageManager.getSelected();
        if ("fr_fr".equals(currentLanguage)) {
            sendMessage(player, "<Vent> " + WHISPERS.getWhisperFr());
        }
        else {
            sendMessage(player, "<Wind> " + WHISPERS.getWhisperEn());
        }
    }

    private static void sendMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }
}
