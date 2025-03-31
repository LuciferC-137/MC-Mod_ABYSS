package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record ThemeIncarnationStart() implements CustomPacketPayload {

    public static final Type<ThemeIncarnationStart> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "theme_incarnation_start"));

    public static final StreamCodec<ByteBuf, ThemeIncarnationStart> STREAM_CODEC
            = CodecBuilders.emptyCodec(ThemeIncarnationStart::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
