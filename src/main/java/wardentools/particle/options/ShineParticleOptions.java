package wardentools.particle.options;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.ParticleRegistry;

public record ShineParticleOptions(Vec3 goal, int color, boolean emissive, boolean hasGoal) implements ParticleOptions {
    public static final MapCodec<ShineParticleOptions> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(
                    Codec.DOUBLE.fieldOf("x").forGetter((ShineParticleOptions o) -> o.goal().x),
                    Codec.DOUBLE.fieldOf("y").forGetter((ShineParticleOptions o) -> o.goal().y),
                    Codec.DOUBLE.fieldOf("z").forGetter((ShineParticleOptions o) -> o.goal().z),
                    Codec.INT.fieldOf("color").forGetter((ShineParticleOptions o) -> o.color),
                    Codec.BOOL.fieldOf("emissive").forGetter((ShineParticleOptions o) -> o.emissive),
                    Codec.BOOL.fieldOf("has_goal").forGetter((ShineParticleOptions o) -> o.hasGoal)
            ).apply(inst, (x, y, z, color, emissive, hasGoal)
                    -> new ShineParticleOptions(new Vec3(x, y, z), color, emissive, hasGoal))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ShineParticleOptions> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.DOUBLE, (ShineParticleOptions o) -> o.goal.x,
                    ByteBufCodecs.DOUBLE, o -> o.goal.y,
                    ByteBufCodecs.DOUBLE, o -> o.goal.z,
                    ByteBufCodecs.INT, o -> o.color,
                    ByteBufCodecs.BOOL, o -> o.emissive,
                    ByteBufCodecs.BOOL, o -> o.hasGoal,
                    (x, y, z, color, emissive, hasGoal)
                            -> new ShineParticleOptions(new Vec3(x, y, z), color, emissive, hasGoal)
            );

    public ShineParticleOptions(Vec3 goal, int color) {
        this(goal, color, true, true);
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return ParticleRegistry.SHINE_PARTICLE.get();
    }
}
