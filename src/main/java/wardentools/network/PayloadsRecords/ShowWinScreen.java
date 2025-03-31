package wardentools.network.PayloadsRecords;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ShowWinScreen(Vector3f respawnPos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ShowWinScreen> TYPE
            = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "show_win_screen"));

    public static final StreamCodec<ByteBuf, ShowWinScreen> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ShowWinScreen::respawnPos,
            ShowWinScreen::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
