package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record WindWhispererMessageSound() implements CustomPacketPayload {

    public static final Type<WindWhispererMessageSound> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "wind_whisperer_message_sound"));

    public static final StreamCodec<ByteBuf, WindWhispererMessageSound> STREAM_CODEC
            = CodecBuilders.emptyCodec(WindWhispererMessageSound::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
