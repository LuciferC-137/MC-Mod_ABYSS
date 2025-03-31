package wardentools.misc.wind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.PayloadsRecords.ParticlesSounds.WindWhispererMessageSound;
import wardentools.sounds.ModSounds;
import wardentools.worldgen.dimension.ModDimensions;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class WhisperManager {
    public static final WhisperManager INSTANCE = new WhisperManager();
    public static final WindWhispers WHISPERS = new WindWhispers();
    private static final int MIN_TIME_BETWEEN_WHISPERS = 500;
    private int nextMinTime = MIN_TIME_BETWEEN_WHISPERS;
    private int timeSinceLastWhisper = 0;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
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

    public void sendRandomWhisperToPlayer(@NotNull ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new WindWhispererMessageSound());
    }

    // This method must only be called externally by packets since this class should only work on server
    public static void sendRandomWhisperToPlayer(@NotNull LocalPlayer player) {
        Minecraft minecraft = Minecraft.getInstance();
        LanguageManager languageManager = minecraft.getLanguageManager();
        String currentLanguage = languageManager.getSelected();
        player.playSound(ModSounds.WIND_WHISPERS.get(), 5f,
                (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);
        if ("fr_fr".equals(currentLanguage)) {
            sendMessage(player, "<Vent> " + WhisperManager.WHISPERS.getWhisperFr());
        }
        else {
            sendMessage(player, "<Wind> " + WhisperManager.WHISPERS.getWhisperEn());
        }
    }

    private static void sendMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }
}
