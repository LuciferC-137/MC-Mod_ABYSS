package wardentools.particle.options;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.NotNull;

import wardentools.particle.ParticleRegistry;

@SuppressWarnings("deprecation")
public record GlyphParticleRotatingOptions(Vec3 center, Vec3 axis, double angularVelocity,
                                           int lifetime, int color, boolean emissive) implements ParticleOptions {

    public static final Codec<GlyphParticleRotatingOptions> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    Codec.DOUBLE.fieldOf("x")
                            .forGetter(o -> o.center().x),
                    Codec.DOUBLE.fieldOf("y")
                            .forGetter(o -> o.center().y),
                    Codec.DOUBLE.fieldOf("z")
                            .forGetter(o -> o.center().z),
                    Codec.DOUBLE.fieldOf("x_axis")
                            .forGetter(o -> o.axis().x),
                    Codec.DOUBLE.fieldOf("y_axis")
                            .forGetter(o -> o.axis().y),
                    Codec.DOUBLE.fieldOf("z_axis")
                            .forGetter(o -> o.axis().z),
                    Codec.DOUBLE.fieldOf("angular_velocity")
                            .forGetter(GlyphParticleRotatingOptions::angularVelocity),
                    Codec.INT.fieldOf("lifetime")
                            .forGetter(GlyphParticleRotatingOptions::lifetime),
                    Codec.INT.fieldOf("color")
                            .forGetter(GlyphParticleRotatingOptions::color),
                    Codec.BOOL.fieldOf("emissive")
                            .forGetter(GlyphParticleRotatingOptions::emissive)
            ).apply(inst, (x, y, z, xAxis, yAxis, zAxis,
                           angularVelocity, lifetime, color, emissive) ->
                    new GlyphParticleRotatingOptions(
                            new Vec3(x, y, z),
                            new Vec3(xAxis, yAxis, zAxis),
                            angularVelocity, lifetime, color, emissive
                    )
            ));

    public static final Deserializer<GlyphParticleRotatingOptions> DESERIALIZER =
            new Deserializer<>() {
                @Override
                public @NotNull GlyphParticleRotatingOptions fromCommand(
                        @NotNull ParticleType<GlyphParticleRotatingOptions> type, StringReader reader)
                        throws CommandSyntaxException {
                    reader.expect(' ');
                    double x = reader.readDouble();
                    reader.expect(' ');
                    double y = reader.readDouble();
                    reader.expect(' ');
                    double z = reader.readDouble();
                    reader.expect(' ');
                    double xAxis = reader.readDouble();
                    reader.expect(' ');
                    double yAxis = reader.readDouble();
                    reader.expect(' ');
                    double zAxis = reader.readDouble();
                    reader.expect(' ');
                    double angularVelocity = reader.readDouble();
                    reader.expect(' ');
                    int lifetime = reader.readInt();
                    reader.expect(' ');
                    int color = reader.readInt();
                    reader.expect(' ');
                    boolean emissive = reader.readBoolean();

                    return new GlyphParticleRotatingOptions(new Vec3(x, y, z), new Vec3(xAxis, yAxis, zAxis),
                            angularVelocity, lifetime, color, emissive);
                }

                @Override
                public @NotNull GlyphParticleRotatingOptions fromNetwork(
                        @NotNull ParticleType<GlyphParticleRotatingOptions> type, FriendlyByteBuf buf) {
                    Vec3 center = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
                    Vec3 axis = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());

                    return new GlyphParticleRotatingOptions(center, axis, buf.readDouble(),
                            buf.readInt(), buf.readInt(), buf.readBoolean());
                }
            };

    public GlyphParticleRotatingOptions(Vec3 center, Vec3 axis, double angularVelocity, int color) {
        this(center, axis, angularVelocity, 120, color, true);
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeDouble(center.x);
        buf.writeDouble(center.y);
        buf.writeDouble(center.z);
        buf.writeDouble(axis.x);
        buf.writeDouble(axis.y);
        buf.writeDouble(axis.z);
        buf.writeDouble(angularVelocity);
        buf.writeInt(lifetime);
        buf.writeInt(color);
        buf.writeBoolean(emissive);
    }

    @Override
    public @NotNull String writeToString() {
        return String.format(
                java.util.Locale.ROOT,
                "%s %s %s %s %s %s %s %d %d %b",
                center.x, center.y, center.z,
                axis.x, axis.y, axis.z,
                angularVelocity, lifetime, color, emissive
        );
    }

    @Override
    public @NotNull ParticleType<GlyphParticleRotatingOptions> getType() {
        return ParticleRegistry.GLYPH_PARTICLE_ROTATING.get();
    }
}
