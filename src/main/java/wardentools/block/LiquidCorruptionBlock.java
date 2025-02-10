package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;
import wardentools.misc.CustomDamageType;
import wardentools.particle.ParticleRegistry;

public class LiquidCorruptionBlock extends LiquidBlock {
    private static final int PARTICLE_PERIOD = 30;
    private static final double PARTICLE_SPEED = 0.1;

    @Deprecated
    public LiquidCorruptionBlock(FlowingFluid flowingFluid, BlockBehaviour.Properties properties) {
        super(flowingFluid, properties);
    }

    public LiquidCorruptionBlock(java.util.function.Supplier<? extends FlowingFluid> supplier,
                                 BlockBehaviour.Properties properties) {
        super(supplier, properties);
    }

    @Override
    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level,
                             @NotNull BlockPos blockPos, @NotNull Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (!entity.level().isClientSide
                && entity instanceof LivingEntity living
                && living.level().getGameTime()%20==1) {
            if (ModEffects.CORRUPTED.getHolder().isEmpty()) return;
            living.addEffect(new MobEffectInstance(ModEffects.CORRUPTED.getHolder().get(),
                        400, 1, false, false), living);
            if (living.level().registryAccess().lookup(Registries.DAMAGE_TYPE).isEmpty()) return;
            Holder<DamageType> corruptedDamageTypeHolder = living.level().registryAccess()
                    .lookup(Registries.DAMAGE_TYPE).get()
                    .wrapAsHolder(CustomDamageType.CORRUPTED_KEY.getOrThrow(level).get());
            living.hurtServer((ServerLevel)level, new DamageSource(corruptedDamageTypeHolder,
                    null, living, null), 3f);
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                  @NotNull Block block, Orientation orientation, boolean isMoving) {
        if (this.anyNeighborLiquid(level, pos)) {
            level.setBlock(pos, BlockRegistry.SOLID_CORRUPTION.get().defaultBlockState(), Block.UPDATE_ALL);
        }
        super.neighborChanged(state, level, pos, block, orientation, isMoving);
    }

    private boolean anyNeighborLiquid(@NotNull LevelReader level, @NotNull BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (level.getBlockState(pos.relative(direction)).is(Blocks.WATER)
                    || level.getBlockState(pos.relative(direction)).is(Blocks.LAVA)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        Vec3 spawnPos = new Vec3(pos.getX(), pos.getY() + 1.0D, pos.getZ());
        if (random.nextInt(PARTICLE_PERIOD)==0){
            level.addParticle(ParticleRegistry.BLACK_CORRUPTION.get(),
                    spawnPos.x + random.nextDouble(),
                    spawnPos.y,
                    spawnPos.z + random.nextDouble(),
                    0, (random.nextDouble() + 0.5) * PARTICLE_SPEED, 0);
        }
    }
}
