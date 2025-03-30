package wardentools.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, ModMain.MOD_ID);

    public static final Supplier<SimpleParticleType> ABYSS_AMBIENT =
            PARTICLE_TYPES.register("abyss_ambient", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> CORRUPTION =
            PARTICLE_TYPES.register("corruption", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> ABYSS_PORTAL =
            PARTICLE_TYPES.register("abyss_portal", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> RADIANCE =
            PARTICLE_TYPES.register("radiance", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> BLACK_CORRUPTION =
            PARTICLE_TYPES.register("black_corruption", () -> new SimpleParticleType(true));

}
