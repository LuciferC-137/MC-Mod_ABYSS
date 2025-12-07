package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record WindWhisperSound(byte stormStatus) implements CustomPacketPayload {

    public static final Type<WindWhisperSound> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "wind_whisper_sound"));

    public static final StreamCodec<ByteBuf, WindWhisperSound> STREAM_CODEC
            = StreamCodec.composite(
                    ByteBufCodecs.BYTE,
                    WindWhisperSound::stormStatus,
                    WindWhisperSound::new
            );

    public WindWhisperSound(@NotNull StormStatus status) {
        this(status.getId());
    }

    public WindWhisperSound() {
        this(StormStatus.NONE);
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public enum StormStatus {
        NONE((byte)0),
        START((byte)1),
        END((byte)2);

        private final byte id;

        StormStatus(byte id) {
            this.id = id;
        }

        public byte getId() {
            return id;
        }

        public static StormStatus fromId(byte id) {
            for (StormStatus s : values()) {
                if (s.id == id) return s;
            }
            return NONE;
        }
    }
}
