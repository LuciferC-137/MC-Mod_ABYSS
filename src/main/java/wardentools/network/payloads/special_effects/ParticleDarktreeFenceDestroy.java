package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ParticleDarktreeFenceDestroy(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "particle_darktree_fence_destroy");

    public static void encode(ParticleDarktreeFenceDestroy msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static ParticleDarktreeFenceDestroy decode(FriendlyByteBuf buf) {
        return new ParticleDarktreeFenceDestroy(buf.readVector3f());
    }
}
