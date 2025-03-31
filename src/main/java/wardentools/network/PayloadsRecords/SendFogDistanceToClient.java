package wardentools.network.PayloadsRecords;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SendFogDistanceToClient(float fogDistance) implements CustomPacketPayload {

    public static final Type<SendFogDistanceToClient> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "send_fog_distance_to_client"));

    public static final StreamCodec<ByteBuf, SendFogDistanceToClient> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            SendFogDistanceToClient::fogDistance,
            SendFogDistanceToClient::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
