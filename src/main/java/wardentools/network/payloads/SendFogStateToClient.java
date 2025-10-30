package wardentools.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SendFogStateToClient(boolean isStorming) implements CustomPacketPayload {

    public static final Type<SendFogStateToClient> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "send_fog_state_to_client"));

    public static final StreamCodec<ByteBuf, SendFogStateToClient> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            SendFogStateToClient::isStorming,
            SendFogStateToClient::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
