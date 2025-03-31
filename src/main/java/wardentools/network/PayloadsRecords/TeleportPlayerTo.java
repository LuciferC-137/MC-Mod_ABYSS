package wardentools.network.PayloadsRecords;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record TeleportPlayerTo(Vector3f respawnPos) implements CustomPacketPayload {

    public static final Type<TeleportPlayerTo> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "teleport_player_to"));

    public static final StreamCodec<ByteBuf, TeleportPlayerTo> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            TeleportPlayerTo::respawnPos,
            TeleportPlayerTo::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
