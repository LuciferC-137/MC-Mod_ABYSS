package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;

public class LiquidCorruptionBlock extends LiquidBlock {

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
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level,
                             @NotNull BlockPos blockPos, @NotNull Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (!entity.level().isClientSide
                && entity instanceof LivingEntity entity1
                && entity1.level().getGameTime()%20==1) {
            entity1.addEffect(new MobEffectInstance(ModEffects.CORRUPTED.get(),
                        400, 1, false, false));
            Holder<DamageType> magicDamageTypeHolder
                    = entity1.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.MAGIC);
            entity1.hurt(new DamageSource(magicDamageTypeHolder,
                    null, entity1, null), 3f);
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
}
