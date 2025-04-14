package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record ThemeIncarnationStop() implements CustomPacketPayload {

    public static final Type<ThemeIncarnationStop> TYPE
            = new Type<>(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "theme_incarnation_stop"));

    public static final StreamCodec<ByteBuf, ThemeIncarnationStop> STREAM_CODEC
            = CodecBuilders.emptyCodec(ThemeIncarnationStop::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
