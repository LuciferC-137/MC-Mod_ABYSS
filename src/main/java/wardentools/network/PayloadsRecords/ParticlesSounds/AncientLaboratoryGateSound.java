package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record AncientLaboratoryGateSound(Vector3f pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<AncientLaboratoryGateSound> TYPE
            = new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ancient_laboratory_gate_sound"));

    public static final StreamCodec<ByteBuf, AncientLaboratoryGateSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            AncientLaboratoryGateSound::pos,
            AncientLaboratoryGateSound::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
