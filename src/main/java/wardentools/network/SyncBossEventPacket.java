package wardentools.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.sounds.ModMusics;

public class SyncBossEventPacket {
    private final int bossId;
    private final boolean isInBossEvent;

    public SyncBossEventPacket(int id, boolean isInBossEvent) {
        this.bossId = id;
        this.isInBossEvent = isInBossEvent;
    }

    public SyncBossEventPacket(FriendlyByteBuf buffer) {
        this.bossId = buffer.readInt();
        this.isInBossEvent = buffer.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(bossId);
        buffer.writeBoolean(isInBossEvent);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level == null) return;
            ContagionIncarnationEntity bossEntity = (ContagionIncarnationEntity)
                    Minecraft.getInstance().level.getEntity(bossId);
            if (bossEntity != null) {
                bossEntity.setClientInBossEvent(isInBossEvent);
            }
            Minecraft.getInstance().getMusicManager().startPlaying(ModMusics.INCARNATION_THEME);
        });
        context.setPacketHandled(true);
    }
}
