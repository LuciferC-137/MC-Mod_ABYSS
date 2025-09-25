package wardentools.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.particle.options.ShineParticleOptions;

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

    public static final RegistryObject<ParticleType<ShineParticleOptions>> SHINE_PARTICLE =
            registerCustom("amethyst_shine", ShineParticleOptions.CODEC, ShineParticleOptions.STREAM_CODEC);


    private static <T extends ParticleOptions> RegistryObject<ParticleType<T>>
        registerCustom(String name, MapCodec<T> codec,
                       StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return PARTICLE_TYPES.register(name, () ->
                new ParticleType<T>(false) {
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
