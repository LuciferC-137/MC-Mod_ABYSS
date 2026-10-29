package wardentools.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.particle.options.GlyphParticleOptions;
import wardentools.particle.options.GlyphParticleRotatingOptions;
import wardentools.particle.options.ShineParticleOptions;

@SuppressWarnings("deprecation")
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

    public static final RegistryObject<ParticleType<GlyphParticleOptions>>
            GLYPH_PARTICLE = registerCustom("glyph", GlyphParticleOptions.DESERIALIZER, GlyphParticleOptions.CODEC);

    public static final RegistryObject<ParticleType<GlyphParticleRotatingOptions>>
            GLYPH_PARTICLE_ROTATING = registerCustom("glyph_rotating",
            GlyphParticleRotatingOptions.DESERIALIZER, GlyphParticleRotatingOptions.CODEC);

    public static final RegistryObject<ParticleType<ShineParticleOptions>>
            SHINE_PARTICLE = registerCustom("shine", ShineParticleOptions.DESERIALIZER, ShineParticleOptions.CODEC);


    private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerCustom(
            String name, ParticleOptions.Deserializer<T> deserializer, Codec<T> codec) {
        return PARTICLE_TYPES.register(name, () -> new ParticleType<T>(false, deserializer) {
            @Override
            public @NotNull Codec<T> codec() {
                return codec;
            }
        });
    }

}
