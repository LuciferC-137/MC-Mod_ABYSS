package wardentools.network.payloads.debug;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

/**
 * Payload sent from client to server to edit a Thryssaryn entity field.
 * @param entityId The entity ID of the Thryssaryn to edit
 * @param fieldId The field identifier (see {@link wardentools.entity.thryssaryn.debug.ThryssarynField})
 * @param newValue The new integer value for the field
 */
public record DebugEditThryssarynToServer(int entityId, int fieldId, int newValue) implements CustomPacketPayload {
    public static final Type<DebugEditThryssarynToServer> TYPE
            = new Type<>(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "debug_edit_thryssaryn"));

    public static final StreamCodec<ByteBuf, DebugEditThryssarynToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, DebugEditThryssarynToServer::entityId,
            ByteBufCodecs.INT, DebugEditThryssarynToServer::fieldId,
            ByteBufCodecs.INT, DebugEditThryssarynToServer::newValue,
            DebugEditThryssarynToServer::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

