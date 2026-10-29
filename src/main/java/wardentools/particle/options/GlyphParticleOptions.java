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
public record GlyphParticleOptions(Vec3 goal, int lifetime, int color, boolean emissive, boolean hasGoal) implements ParticleOptions {

    public static final Codec<GlyphParticleOptions> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.DOUBLE.fieldOf("x")
                            .forGetter(o -> o.goal().x),
                    Codec.DOUBLE.fieldOf("y")
                            .forGetter(o -> o.goal().y),
                    Codec.DOUBLE.fieldOf("z")
                            .forGetter(o -> o.goal().z),
                    Codec.INT.fieldOf("lifetime")
                            .forGetter(GlyphParticleOptions::lifetime),
                    Codec.INT.fieldOf("color")
                            .forGetter(GlyphParticleOptions::color),
                    Codec.BOOL.fieldOf("emissive")
                            .forGetter(GlyphParticleOptions::emissive),
                    Codec.BOOL.fieldOf("has_goal")
                            .forGetter(GlyphParticleOptions::hasGoal)
            ).apply(instance, (x, y, z, lifetime, color, emissive, hasGoal) ->
                    new GlyphParticleOptions(new Vec3(x, y, z), lifetime, color, emissive, hasGoal)
            ));

    public static final Deserializer<GlyphParticleOptions> DESERIALIZER =
            new Deserializer<>() {
                @Override
                public @NotNull GlyphParticleOptions fromCommand(@NotNull ParticleType<GlyphParticleOptions> type, StringReader reader
                ) throws CommandSyntaxException {
                    reader.expect(' ');
                    double x = reader.readDouble();
                    reader.expect(' ');
                    double y = reader.readDouble();
                    reader.expect(' ');
                    double z = reader.readDouble();
                    reader.expect(' ');
                    int lifetime = reader.readInt();
                    reader.expect(' ');
                    int color = reader.readInt();
                    reader.expect(' ');
                    boolean emissive = reader.readBoolean();
                    reader.expect(' ');
                    boolean hasGoal = reader.readBoolean();

                    return new GlyphParticleOptions(new Vec3(x, y, z),
                            lifetime, color, emissive, hasGoal);
                }

                @Override
                public @NotNull GlyphParticleOptions fromNetwork(@NotNull ParticleType<GlyphParticleOptions> type, FriendlyByteBuf buf) {
                    double x = buf.readDouble();
                    double y = buf.readDouble();
                    double z = buf.readDouble();
                    int lifetime = buf.readInt();
                    int color = buf.readInt();
                    boolean emissive = buf.readBoolean();
                    boolean hasGoal = buf.readBoolean();

                    return new GlyphParticleOptions(new Vec3(x, y, z), lifetime, color, emissive, hasGoal);
                }
            };

    public GlyphParticleOptions(Vec3 goal, int color) {
        this(goal, 70, color, true, true);
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeDouble(goal.x);
        buf.writeDouble(goal.y);
        buf.writeDouble(goal.z);
        buf.writeInt(lifetime);
        buf.writeInt(color);
        buf.writeBoolean(emissive);
        buf.writeBoolean(hasGoal);
    }

    @Override
    public @NotNull String writeToString() {
        return String.format(
                java.util.Locale.ROOT,
                "%s %s %s %d %d %b %b",
                goal.x, goal.y, goal.z,
                lifetime, color, emissive, hasGoal
        );
    }

    @Override
    public @NotNull ParticleType<GlyphParticleOptions> getType() {
        return ParticleRegistry.GLYPH_PARTICLE.get();
    }
}

