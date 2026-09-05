package wardentools.network;

import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class CodecBuilders {

    public static <T> void encodeEmpty(T payload, FriendlyByteBuf buf) {
        // Intentionally empty: this packet carries no data.
    }

    public static <T> T decodeEmpty(FriendlyByteBuf buf, Supplier<T> constructor) {
        return constructor.get();
    }
}
