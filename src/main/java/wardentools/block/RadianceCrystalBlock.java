package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.ParticleRegistry;

public class RadianceCrystalBlock extends CrystalBlock {
    private static final double SPEED = 0.1;
    private static final int PERIOD = 40;

    public RadianceCrystalBlock(float height, float length, Properties properties) {
        super(height, length, 8, properties);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                            @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        Vec3 spawnPos = pos.getCenter();
        if (random.nextInt(PERIOD)==0){
            level.addParticle(ParticleRegistry.RADIANCE.get(),
                    spawnPos.x, spawnPos.y, spawnPos.z,
                    (random.nextDouble() - 0.5) * SPEED,
                    (random.nextDouble() - 0.5) * SPEED,
                    (random.nextDouble() - 0.5) * SPEED);
        }
    }
}
