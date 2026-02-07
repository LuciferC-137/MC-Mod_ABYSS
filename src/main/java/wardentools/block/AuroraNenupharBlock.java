package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.misc.Crystal;
import wardentools.particle.options.PrismaticFireflyOptions;
import wardentools.worldgen.biome.ModBiomes;

public class AuroraNenupharBlock extends BushBlock {
    public static final MapCodec<AuroraNenupharBlock> CODEC = simpleCodec(AuroraNenupharBlock::new);
    protected static final VoxelShape AABB = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 1.5F, 15.0F);

    private static final int SPECIAL_COLOR_RARITY = 6;
    private static final int PARTICLE_RADIUS = 3;

    public @NotNull MapCodec<AuroraNenupharBlock> codec() {
        return CODEC;
    }


    public AuroraNenupharBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(Crystal.VARIANT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(Crystal.VARIANT);
    }

    @Override
    protected void entityInside(@NotNull BlockState state, @NotNull Level level,
                                @NotNull BlockPos pos, @NotNull Entity entity) {
        super.entityInside(state, level, pos, entity);
        if (level instanceof ServerLevel && entity instanceof Boat) {
            level.destroyBlock(new BlockPos(pos), true, entity);
        }

    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state,
                                           @NotNull BlockGetter level, @NotNull BlockPos pos,
                                           @NotNull CollisionContext context) {
        return AABB;
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state,
                                 @NotNull BlockGetter level, @NotNull BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos);
        FluidState fluidstate1 = level.getFluidState(pos.above());
        return (fluidstate.getType() == Fluids.WATER || state.getBlock() instanceof IceBlock)
                && fluidstate1.getType() == Fluids.EMPTY;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return getRandomColoredState(context.getLevel().getRandom());
    }

    public static @NotNull BlockState getRandomColoredState(@NotNull RandomSource random) {
        if (random.nextInt(SPECIAL_COLOR_RARITY) != 0) {
            return BlockRegistry.AURORA_NENUPHAR.get().defaultBlockState()
                    .setValue(Crystal.VARIANT, 0);
        }
        return BlockRegistry.AURORA_NENUPHAR.get().defaultBlockState()
                .setValue(Crystal.VARIANT,
                        Crystal.VARIANT.getPossibleValues().stream()
                                .toList().get(random.nextInt(1, Crystal.VARIANT.getPossibleValues().size())));
    }

    public static int getColor(@NotNull BlockState state,
                               @Nullable BlockAndTintGetter blockAndTintGetter,
                               @Nullable BlockPos blockPos, int index) {
        if (index != 1) return -1;
        if (!state.hasProperty(Crystal.VARIANT)) return -1;
        return Crystal.fromBlockProperty(state).getColor();
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        level.addParticle(new PrismaticFireflyOptions(false,
                        Crystal.fromBlockProperty(state).getColor()),
                pos.getX() + 0.5F + (random.nextDouble() - 0.5F) * (float)PARTICLE_RADIUS * 2F,
                pos.getY() + 0.5F + random.nextDouble() * (float)PARTICLE_RADIUS / 2F,
                pos.getZ() + 0.5F + (random.nextDouble() - 0.5F) * (float)PARTICLE_RADIUS * 2F,
                0, 0, 0);
    }

    public static int getColor(ItemStack stack, int index) {
        return Crystal.getDefault().getColor();
    }
}
