package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.block.BlockRegistry;
import wardentools.particle.ParticleRegistry;

import java.io.IOException;

public class ParticleDarktreFenceDestroyed {
    private static final BlockParticleOption FENCE_PARTICLE
            = new BlockParticleOption(ParticleTypes.BLOCK,
            BlockRegistry.DARKTREE_FENCE.get().defaultBlockState());
    private final Vec3 source;

    public ParticleDarktreFenceDestroyed(Vec3 source) {this.source = source;}

    public ParticleDarktreFenceDestroyed(FriendlyByteBuf buffer) {this.source = buffer.readVec3();}

    public void encode(FriendlyByteBuf buffer) {buffer.writeVec3(this.source);}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleDarktreFenceDestroyed msg) {
        Vec3 source = msg.source;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                for (int i = 0; i < 8; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * 0.05;
                    double offsetY = (level.random.nextDouble() - 0.5) * 0.05;
                    double offsetZ = (level.random.nextDouble() - 0.5) * 0.05;
                    level.addParticle(FENCE_PARTICLE, source.x, source.y, source.z,
                            offsetX, offsetY, offsetZ);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
