package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ParticleDarktreeFenceDestroy(Vector3f pos) implements CustomPacketPayload {

    public static final Type<ParticleDarktreeFenceDestroy> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "particle_darktree_fence_destroy"));

    public static final StreamCodec<ByteBuf, ParticleDarktreeFenceDestroy> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ParticleDarktreeFenceDestroy::pos,
            ParticleDarktreeFenceDestroy::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
