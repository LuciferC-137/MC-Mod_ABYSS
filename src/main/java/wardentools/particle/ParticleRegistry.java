package wardentools.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.particle.options.GlyphParticleOptions;
import wardentools.particle.options.GlyphParticleRotatingOptions;
import wardentools.particle.options.ShineParticleOptions;

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

    public static final Supplier<ParticleType<ShineParticleOptions>> SHINE_PARTICLE =
            registerCustom("shine", ShineParticleOptions.CODEC, ShineParticleOptions.STREAM_CODEC);
    public static final Supplier<ParticleType<GlyphParticleOptions>> GLYPH_PARTICLE =
            registerCustom("glyph", GlyphParticleOptions.CODEC, GlyphParticleOptions.STREAM_CODEC);
    public static final Supplier<ParticleType<GlyphParticleRotatingOptions>> GLYPH_PARTICLE_ROTATING =
            registerCustom("glyph_rotating", GlyphParticleRotatingOptions.CODEC, GlyphParticleRotatingOptions.STREAM_CODEC);


    private static <T extends ParticleOptions> Supplier<ParticleType<T>>
        registerCustom(String name, MapCodec<T> codec,
                       StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return PARTICLE_TYPES.register(name, () ->
                new ParticleType<T>(true) {
                    @Override
                    public @NotNull MapCodec<T> codec() {
                        return codec;
                    }
                    @Override
                    public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
                        return streamCodec;
                    }
                }
        );
    }

}
