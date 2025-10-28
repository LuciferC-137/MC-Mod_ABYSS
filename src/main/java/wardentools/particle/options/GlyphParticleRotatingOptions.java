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

public record GlyphParticleRotatingOptions(Vec3 center, Vec3 axis, double angularVelocity, int lifetime, int color, boolean emissive) implements ParticleOptions {
    public static final MapCodec<GlyphParticleRotatingOptions> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(
                    Codec.DOUBLE.fieldOf("x").forGetter((GlyphParticleRotatingOptions o) -> o.center().x),
                    Codec.DOUBLE.fieldOf("y").forGetter((GlyphParticleRotatingOptions o) -> o.center().y),
                    Codec.DOUBLE.fieldOf("z").forGetter((GlyphParticleRotatingOptions o) -> o.center().z),
                    Codec.DOUBLE.fieldOf("x_axis").forGetter((GlyphParticleRotatingOptions o) -> o.center().x),
                    Codec.DOUBLE.fieldOf("y_axis").forGetter((GlyphParticleRotatingOptions o) -> o.center().y),
                    Codec.DOUBLE.fieldOf("z_axis").forGetter((GlyphParticleRotatingOptions o) -> o.center().z),
                    Codec.DOUBLE.fieldOf("angular_velocity").forGetter((GlyphParticleRotatingOptions o) -> o.angularVelocity),
                    Codec.INT.fieldOf("lifetime").forGetter((GlyphParticleRotatingOptions o) -> o.lifetime),
                    Codec.INT.fieldOf("color").forGetter((GlyphParticleRotatingOptions o) -> o.color),
                    Codec.BOOL.fieldOf("emissive").forGetter((GlyphParticleRotatingOptions o) -> o.emissive)
            ).apply(inst, (x, y, z, xAxis, yAxis, zAxis,  angularVelocity, lifetime, color, emissive)
                    -> new GlyphParticleRotatingOptions(new Vec3(x, y, z), new Vec3(xAxis, yAxis, zAxis), angularVelocity, lifetime, color, emissive))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, GlyphParticleRotatingOptions> STREAM_CODEC =
            StreamCodec.of(
                    (buf, o) -> {
                        buf.writeDouble(o.center.x);
                        buf.writeDouble(o.center.y);
                        buf.writeDouble(o.center.z);
                        buf.writeDouble(o.axis.x);
                        buf.writeDouble(o.axis.y);
                        buf.writeDouble(o.axis.z);
                        buf.writeDouble(o.angularVelocity);
                        buf.writeInt(o.lifetime);
                        buf.writeInt(o.color);
                        buf.writeBoolean(o.emissive);
                    },
                    buf -> {
                        double x = buf.readDouble();
                        double y = buf.readDouble();
                        double z = buf.readDouble();
                        double xAxis = buf.readDouble();
                        double yAxis = buf.readDouble();
                        double zAxis = buf.readDouble();
                        double angularVelocity = buf.readDouble();
                        int lifetime = buf.readInt();
                        int color = buf.readInt();
                        boolean emissive = buf.readBoolean();
                        return new GlyphParticleRotatingOptions(new Vec3(x, y, z), new Vec3(xAxis, yAxis, zAxis),
                                angularVelocity, lifetime, color, emissive);
                    }
            );

    public GlyphParticleRotatingOptions(Vec3 center, Vec3 axis, double angularVelocity, int color) {
        this(center, axis, angularVelocity, 120, color, true);
    }

    public static GlyphParticleRotatingOptions onXAxis(Vec3 center, double angularVelocity, int color) {
        return new GlyphParticleRotatingOptions(center, new Vec3(1, 0, 0), angularVelocity, color);
    }

    public static GlyphParticleRotatingOptions onYAxis(Vec3 center, double angularVelocity, int color) {
        return new GlyphParticleRotatingOptions(center, new Vec3(0, 1, 0), angularVelocity, color);
    }

    public static GlyphParticleRotatingOptions onZAxis(Vec3 center, double angularVelocity, int color) {
        return new GlyphParticleRotatingOptions(center, new Vec3(0, 0, 1), angularVelocity, color);
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return ParticleRegistry.GLYPH_PARTICLE_ROTATING.get();
    }
}
