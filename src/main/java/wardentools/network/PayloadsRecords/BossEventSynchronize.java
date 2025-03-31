package wardentools.network.PayloadsRecords;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record BossEventSynchronize(int bossId, boolean playerInBossEvent) implements CustomPacketPayload {

    public static final Type<BossEventSynchronize> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "boss_event_sync"));

    public static final StreamCodec<ByteBuf, BossEventSynchronize> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            BossEventSynchronize::bossId,
            ByteBufCodecs.BOOL,
            BossEventSynchronize::playerInBossEvent,
            BossEventSynchronize::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
