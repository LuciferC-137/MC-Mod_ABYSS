package wardentools.particle.options;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.ParticleRegistry;

public record PrismaticFireflyOptions(boolean colorFixed, int color) implements ParticleOptions {
    public static final MapCodec<PrismaticFireflyOptions> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(
                    Codec.BOOL.fieldOf("color_fixed").forGetter(PrismaticFireflyOptions::colorFixed),
                    Codec.INT.fieldOf("color").forGetter(PrismaticFireflyOptions::color)
            ).apply(inst, PrismaticFireflyOptions::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, PrismaticFireflyOptions> STREAM_CODEC =
            StreamCodec.of(
                    (buf, o) -> {
                        buf.writeBoolean(o.colorFixed);
                        buf.writeInt(o.color);
                    },
                    buf -> {
                        boolean colorFixed = buf.readBoolean();
                        int color = buf.readInt();
                        return new PrismaticFireflyOptions(colorFixed, color);
                    }
            );

    @Override
    public @NotNull ParticleType<?> getType() {
        return ParticleRegistry.PRISMATIC_FIREFLY_PARTICLE.get();
    }
}
