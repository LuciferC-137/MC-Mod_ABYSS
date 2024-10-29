package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;
import wardentools.misc.CustomDamageType;
import wardentools.particle.ParticleRegistry;

public class LiquidCorruptionBlock extends LiquidBlock {
    private static final int PARTICLE_PERDIOD = 30;
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
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter getter,
                                  @NotNull BlockPos pos, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level,
                             @NotNull BlockPos blockPos, @NotNull Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (!entity.level().isClientSide
                && entity instanceof LivingEntity living
                && living.level().getGameTime()%20==1) {
            living.addEffect(new MobEffectInstance(ModEffects.CORRUPTED.get(),
                        400, 1, false, false));
            Holder<DamageType> corruptedDamageTypeHolder = living.level().registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);
            living.hurt(new DamageSource(corruptedDamageTypeHolder,
                    null, living, null), 3f);
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                @NotNull Block block, @NotNull BlockPos neighborPos, boolean isMoving) {
        BlockState neighborState = level.getBlockState(neighborPos);
        if (neighborState.is(Blocks.WATER) || neighborState.is(Blocks.LAVA)) {
            level.setBlock(pos, BlockRegistry.SOLID_CORRUPTION.get().defaultBlockState(), Block.UPDATE_ALL);
        }
        super.neighborChanged(state, level, pos, block, neighborPos, isMoving);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        Vec3 spawnPos = new Vec3(pos.getX(), pos.getY() + 1.0D, pos.getZ());
        if (random.nextInt(PARTICLE_PERDIOD)==0){
            level.addParticle(ParticleRegistry.BLACK_CORRUPTION.get(),
                    spawnPos.x + random.nextDouble(),
                    spawnPos.y,
                    spawnPos.z + random.nextDouble(),
                    0, (random.nextDouble() + 0.5) * PARTICLE_SPEED, 0);
        }
    }
}
