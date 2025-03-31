package wardentools.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CodecBuilders {

    public static <T extends CustomPacketPayload> StreamCodec<ByteBuf, T> emptyCodec(Supplier<T> constructor) {
        return new StreamCodec<>() {
            @Override
            public void encode(@NotNull ByteBuf buf, @NotNull T payload) {

            }
            @Override
            public @NotNull T decode(@NotNull ByteBuf buf) {
                return constructor.get();
            }
        };
    }
}
