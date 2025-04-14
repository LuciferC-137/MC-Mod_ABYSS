package wardentools.network;

import com.mojang.logging.LogUtils;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.gui.winscreen.CustomWinScreen;
import wardentools.network.PayloadsRecords.*;
import wardentools.weather.AbyssWeatherEventClient;

@OnlyIn(Dist.CLIENT)
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

    public static void updateFogDistance(SendFogDistanceToClient msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            AbyssWeatherEventClient.CLIENT_WEATHER.setServerFogDistance(msg.fogDistance());
        }, ctx);
    }

    public static void switchCamera(SwitchCamera msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player().level().isClientSide()){
                if (Minecraft.getInstance().options.getCameraType() == CameraType.THIRD_PERSON_BACK) {
                    Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
                } else if (Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON) {
                    Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
                }
            }
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
