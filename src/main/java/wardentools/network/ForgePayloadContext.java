package wardentools.network;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ForgePayloadContext {
    private final Supplier<NetworkEvent.Context> contextSupplier;
    private final Supplier<Player> playerSupplier;

    private ForgePayloadContext(Supplier<NetworkEvent.Context> contextSupplier, Supplier<Player> playerSupplier) {
        this.contextSupplier = contextSupplier;
        this.playerSupplier = playerSupplier;
    }

    public static ForgePayloadContext server(Supplier<NetworkEvent.Context> contextSupplier) {
        return new ForgePayloadContext(contextSupplier,
                () -> Objects.requireNonNull(contextSupplier.get().getSender(), "Missing server packet sender"));
    }

    public static ForgePayloadContext client(Supplier<NetworkEvent.Context> contextSupplier, Supplier<Player> playerSupplier) {
        return new ForgePayloadContext(contextSupplier,
                () -> Objects.requireNonNull(playerSupplier.get(), "Missing client packet player"));
    }

    public static ForgePayloadContext client(Supplier<NetworkEvent.Context> contextSupplier) {
        return client(contextSupplier, ForgePayloadContext::resolveClientPlayer);
    }

    public Player player() {
        return playerSupplier.get();
    }

    public CompletableFuture<Void> enqueueWork(Runnable runnable) {
        return contextSupplier.get().enqueueWork(runnable);
    }

    public void disconnect(Component reason) {
        contextSupplier.get().getNetworkManager().disconnect(reason);
    }

    public void setPacketHandled() {
        contextSupplier.get().setPacketHandled(true);
    }

    private static Player resolveClientPlayer() {
        try {
            Class<?> minecraftClass = Class.forName("net.minecraft.client.Minecraft");
            Object minecraft = minecraftClass.getMethod("getInstance").invoke(null);
            return (Player) minecraftClass.getField("player").get(minecraft);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Client player is unavailable for packet handling", e);
        }
    }
}


