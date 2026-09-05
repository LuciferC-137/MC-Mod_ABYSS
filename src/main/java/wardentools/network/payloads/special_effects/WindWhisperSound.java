package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record WindWhisperSound(byte stormStatus) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "wind_whisper_sound");

    public static void encode(WindWhisperSound msg, FriendlyByteBuf buf) {
        buf.writeByte(msg.stormStatus());
    }

    public static WindWhisperSound decode(FriendlyByteBuf buf) {
        return new WindWhisperSound(buf.readByte());
    }

    public WindWhisperSound(@NotNull StormStatus status) {
        this(status.getId());
    }

    public WindWhisperSound() {
        this(StormStatus.NONE);
    }

    public enum StormStatus {
        NONE((byte) 0),
        START((byte) 1),
        END((byte) 2);

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
