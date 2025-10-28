package wardentools.particle.options;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.ParticleRegistry;

public record GlyphParticleOptions(Vec3 goal, int lifetime, int color, boolean emissive, boolean hasGoal) implements ParticleOptions {
    public static final MapCodec<GlyphParticleOptions> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(
                    Codec.DOUBLE.fieldOf("x").forGetter((GlyphParticleOptions o) -> o.goal().x),
                    Codec.DOUBLE.fieldOf("y").forGetter((GlyphParticleOptions o) -> o.goal().y),
                    Codec.DOUBLE.fieldOf("z").forGetter((GlyphParticleOptions o) -> o.goal().z),
                    Codec.INT.fieldOf("lifetime").forGetter((GlyphParticleOptions o) -> o.lifetime),
                    Codec.INT.fieldOf("color").forGetter((GlyphParticleOptions o) -> o.color),
                    Codec.BOOL.fieldOf("emissive").forGetter((GlyphParticleOptions o) -> o.emissive),
                    Codec.BOOL.fieldOf("has_goal").forGetter((GlyphParticleOptions o) -> o.hasGoal)
            ).apply(inst, (x, y, z, lifetime, color, emissive, hasGoal)
                    -> new GlyphParticleOptions(new Vec3(x, y, z), lifetime, color, emissive, hasGoal))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, GlyphParticleOptions> STREAM_CODEC =
            StreamCodec.of(
                    (buf, o) -> {
                        buf.writeDouble(o.goal.x);
                        buf.writeDouble(o.goal.y);
                        buf.writeDouble(o.goal.z);
                        buf.writeInt(o.lifetime);
                        buf.writeInt(o.color);
                        buf.writeBoolean(o.emissive);
                        buf.writeBoolean(o.hasGoal);
                    },
                    buf -> {
                        double x = buf.readDouble();
                        double y = buf.readDouble();
                        double z = buf.readDouble();
                        int lifetime = buf.readInt();
                        int color = buf.readInt();
                        boolean emissive = buf.readBoolean();
                        boolean hasGoal = buf.readBoolean();
                        return new GlyphParticleOptions(new Vec3(x, y, z), lifetime, color, emissive, hasGoal);
                    }
            );

    public GlyphParticleOptions(Vec3 goal, int color) {
        this(goal, 70, color, true, true);
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return ParticleRegistry.GLYPH_PARTICLE.get();
    }
}
