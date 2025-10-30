package wardentools.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record SwitchCamera() implements CustomPacketPayload {

    public static final Type<SwitchCamera> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "switch_camera"));


    public static final StreamCodec<ByteBuf, SwitchCamera> STREAM_CODEC
            = CodecBuilders.emptyCodec(SwitchCamera::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
