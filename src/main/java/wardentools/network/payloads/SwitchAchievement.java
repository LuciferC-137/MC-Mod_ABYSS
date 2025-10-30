package wardentools.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SwitchAchievement(int index) implements CustomPacketPayload {

    public static final Type<SwitchAchievement> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "switch_achievement"));

    public static final StreamCodec<ByteBuf, SwitchAchievement> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SwitchAchievement::index,
            SwitchAchievement::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
