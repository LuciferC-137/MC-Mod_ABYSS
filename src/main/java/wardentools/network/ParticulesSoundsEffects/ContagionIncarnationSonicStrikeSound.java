package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.sounds.ModSounds;

public class ContagionIncarnationSonicStrikeSound {
    private final Vec3 source;

    public ContagionIncarnationSonicStrikeSound(Vec3 source) {this.source = source;}

    public ContagionIncarnationSonicStrikeSound(FriendlyByteBuf buffer) {this.source = buffer.readVec3();}

    public void encode(FriendlyByteBuf buffer) {buffer.writeVec3(this.source);}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ContagionIncarnationSonicStrikeSound msg) {
        Vec3 source = msg.source;
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            BlockPos pos = new BlockPos((int)source.x(), (int)source.y(), (int)source.z());
            level.playLocalSound(pos, ModSounds.SONIC_STRIKE.get(), SoundSource.HOSTILE,
                    4f, 1.0f, false);
        }
    }
}
