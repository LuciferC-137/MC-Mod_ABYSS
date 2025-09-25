package wardentools.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModMain.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ABYSS_AMBIENT =
            PARTICLE_TYPES.register("abyss_ambient", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CORRUPTION =
            PARTICLE_TYPES.register("corruption", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ABYSS_PORTAL =
            PARTICLE_TYPES.register("abyss_portal", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> RADIANCE =
            PARTICLE_TYPES.register("radiance", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLACK_CORRUPTION =
            PARTICLE_TYPES.register("black_corruption", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> AMETHYST_SHINE =
            PARTICLE_TYPES.register("amethyst_shine", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CITRINE_SHINE =
            PARTICLE_TYPES.register("citrine_shine", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> RUBY_SHINE =
            PARTICLE_TYPES.register("ruby_shine", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MALACHITE_SHINE =
            PARTICLE_TYPES.register("malachite_shine", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ECHO_SHINE =
            PARTICLE_TYPES.register("echo_shine", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PALE_SHINE =
            PARTICLE_TYPES.register("pale_shine", () -> new SimpleParticleType(true));


}
