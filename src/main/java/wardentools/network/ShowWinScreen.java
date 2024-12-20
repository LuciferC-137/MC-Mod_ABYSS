package wardentools.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.gui.winscreen.CustomWinScreen;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

import java.util.Objects;

public class ShowWinScreen {
    private final int x, y, z;

    public ShowWinScreen(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ShowWinScreen(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    public ShowWinScreen(FriendlyByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public static void encode(ShowWinScreen msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.x);
        buffer.writeInt(msg.y);
        buffer.writeInt(msg.z);
    }

    public static ShowWinScreen decode(FriendlyByteBuf buffer) {
        return new ShowWinScreen(buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    public static void handlePacket(ShowWinScreen msg) {
        Minecraft minecraft = Minecraft.getInstance();
        CustomWinScreen winScreen = new CustomWinScreen(true, () -> {
            PacketHandler.sendToServer(
                    new TeleportPlayerTo(msg.x, msg.y, msg.z));
            minecraft.setScreen((Screen)null);
        });
        winScreen.init(minecraft, minecraft.getWindow().getGuiScaledWidth(),
                minecraft.getWindow().getGuiScaledHeight());
        minecraft.setScreen(winScreen);
    }
}
