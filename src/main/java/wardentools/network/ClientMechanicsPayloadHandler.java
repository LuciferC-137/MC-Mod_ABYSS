package wardentools.network;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.gui.winscreen.CustomWinScreen;
import wardentools.network.PayloadsRecords.BossEventSynchronize;
import wardentools.network.PayloadsRecords.SendFogDistanceToClient;
import wardentools.network.PayloadsRecords.ShowWinScreen;
import wardentools.network.PayloadsRecords.TeleportPlayerTo;
import wardentools.weather.AbyssWeatherEvent;

public class ClientMechanicsPayloadHandler {

    public static void showWinScreen(ShowWinScreen msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            CustomWinScreen winScreen = new CustomWinScreen(true, () -> {
                PacketDistributor.sendToServer(new TeleportPlayerTo(msg.respawnPos()));
                minecraft.setScreen((Screen)null);
            });
            winScreen.init(minecraft, minecraft.getWindow().getGuiScaledWidth(),
                    minecraft.getWindow().getGuiScaledHeight());
            minecraft.setScreen(winScreen);
        }, ctx);
    }

    public static void bossEventSynchronize(BossEventSynchronize msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (Minecraft.getInstance().level == null) return;
            ContagionIncarnationEntity bossEntity = (ContagionIncarnationEntity)
                    Minecraft.getInstance().level.getEntity(msg.bossId());
            if (bossEntity != null) {
                bossEntity.setClientInBossEvent(msg.playerInBossEvent());
            }
            if (!msg.playerInBossEvent()) {
                Minecraft.getInstance().getMusicManager().stopPlaying();
            }
        }, ctx);
    }

    public static void updateFogDistance(SendFogDistanceToClient msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            AbyssWeatherEvent.CLIENT_WEATHER.setServerFogDistance(msg.fogDistance());
        }, ctx);
    }

    private static void handleDataOnNetwork(Runnable run, final IPayloadContext ctx) {
        ctx.enqueueWork(run)
                .exceptionally(e -> {
                    LogUtils.getLogger().error("Dive Into the Abyss networking failed{}", e.getMessage());
                    ctx.disconnect(Component.literal("Dive Into the Abyss networking failed"));
                    return null;
                });
    }
}
