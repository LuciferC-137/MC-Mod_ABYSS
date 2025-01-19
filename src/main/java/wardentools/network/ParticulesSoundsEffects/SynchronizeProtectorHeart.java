package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.blockentity.ProtectorInvokerBlockEntity;

import java.io.IOException;

public class SynchronizeProtectorHeart {
    private final BlockPos pos;
    private final float health;

    public SynchronizeProtectorHeart(BlockPos pos, float health) {this.pos = pos; this.health = health;}

    public SynchronizeProtectorHeart(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
        this.health = buffer.readFloat();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
        buffer.writeFloat(this.health);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(SynchronizeProtectorHeart msg) {
        BlockPos pos = msg.pos;
        float health = msg.health;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                if (level.getBlockEntity(pos) instanceof ProtectorInvokerBlockEntity invoker) {
                    invoker.saveHealth(health);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
