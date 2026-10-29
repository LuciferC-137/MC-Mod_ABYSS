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

public record ShineParticleOptions(Vec3 goal, int color, boolean emissive, boolean hasGoal) implements ParticleOptions {

    public static final Codec<ShineParticleOptions> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    Codec.DOUBLE.fieldOf("x")
                            .forGetter(o -> o.goal().x),
                    Codec.DOUBLE.fieldOf("y")
                            .forGetter(o -> o.goal().y),
                    Codec.DOUBLE.fieldOf("z")
                            .forGetter(o -> o.goal().z),
                    Codec.INT.fieldOf("color")
                            .forGetter(ShineParticleOptions::color),
                    Codec.BOOL.fieldOf("emissive")
                            .forGetter(ShineParticleOptions::emissive),
                    Codec.BOOL.fieldOf("has_goal")
                            .forGetter(ShineParticleOptions::hasGoal)
            ).apply(inst, (x, y, z, color, emissive, hasGoal) ->
                    new ShineParticleOptions(
                            new Vec3(x, y, z),
                            color, emissive, hasGoal
                    )
            ));

    public static final Deserializer<ShineParticleOptions> DESERIALIZER =
            new Deserializer<>() {
                @Override
                public @NotNull ShineParticleOptions fromCommand(@NotNull ParticleType<ShineParticleOptions> type,
                                                                 StringReader reader) throws CommandSyntaxException {
                    reader.expect(' ');
                    double x = reader.readDouble();
                    reader.expect(' ');
                    double y = reader.readDouble();
                    reader.expect(' ');
                    double z = reader.readDouble();
                    reader.expect(' ');
                    int color = reader.readInt();
                    reader.expect(' ');
                    boolean emissive = reader.readBoolean();
                    reader.expect(' ');
                    boolean hasGoal = reader.readBoolean();

                    return new ShineParticleOptions(new Vec3(x, y, z), color, emissive, hasGoal);
                }

                @Override
                public ShineParticleOptions fromNetwork(ParticleType<ShineParticleOptions> type, FriendlyByteBuf buf) {
                    return new ShineParticleOptions(
                            new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()),
                            buf.readInt(),
                            buf.readBoolean(),
                            buf.readBoolean()
                    );
                }
            };

    public ShineParticleOptions(Vec3 goal, int color) {
        this(goal, color, true, true);
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeDouble(goal.x);
        buf.writeDouble(goal.y);
        buf.writeDouble(goal.z);
        buf.writeInt(color);
        buf.writeBoolean(emissive);
        buf.writeBoolean(hasGoal);
    }

    @Override
    public @NotNull String writeToString() {
        return String.format(
                java.util.Locale.ROOT,
                "%s %s %s %d %b %b",
                goal.x, goal.y, goal.z,
                color, emissive, hasGoal
        );
    }

    @Override
    public @NotNull ParticleType<ShineParticleOptions> getType() {
        return ParticleRegistry.SHINE_PARTICLE.get();
    }
}
