package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.CrystalInfuserBlockEntity;
import wardentools.misc.Crystal;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleShineExplosion;
import wardentools.particle.ModParticleUtils;
import wardentools.particle.options.GlyphParticleRotatingOptions;
import wardentools.particle.options.ShineParticleOptions;

public class CrystalInfuserBlock extends HorizontalDirectionalBlock implements EntityBlock {
    private static final MapCodec<CrystalInfuserBlock> CODEC
            = simpleCodec(CrystalInfuserBlock::new);
    public static final EnumProperty<Crystal> CRYSTAL;
    private static final Vec3[] positions = new Vec3[] {
            new Vec3(10.5F, 10F + 0.35F, 10.5F),
            new Vec3(5.5F, 10F + 0.35F, 5.5F),
            new Vec3(10.5F, 10F + 0.35F, 5.5F),
            new Vec3(5.5F, 10F + 0.35F, 10.5F),
            new Vec3(8F, 17F, 8F)
    };
    private static final float shineSpeed = 0.04F;
    private static final float explosionSpeed = 0.1F;

    static {CRYSTAL = EnumProperty.create("crystal_index", Crystal.class);}

    public CrystalInfuserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any())
                .setValue(CRYSTAL, Crystal.getDefault())
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<CrystalInfuserBlock> codec() {return CODEC;}

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return Block.box(1.0D, 0.0D, 1.0D,
                15.0D, 12.0D, 15.0D);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack,
                                                       @NotNull BlockState state,
                                                       @NotNull Level level,
                                                       @NotNull BlockPos pos,
                                                       @NotNull Player player,
                                                       @NotNull InteractionHand hand,
                                                       @NotNull BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrystalInfuserBlockEntity infuser) {
            if (stack.isEmpty()) {
                ItemStack removedItem = infuser.removeItemInOrder();
                if (!removedItem.isEmpty()) {
                    for (int i = 0; i < player.getInventory().items.size(); i++) {
                        if (player.getInventory().items.get(i).is(removedItem.getItem())) {
                            player.getInventory().items.get(i).grow(1);
                            return ItemInteractionResult.SUCCESS;
                        }
                    }
                    player.setItemInHand(hand, removedItem);
                    return ItemInteractionResult.SUCCESS;
                }
            } else if (infuser.setItemInAvailableSlot(stack)) {
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(CRYSTAL, FACING);
        super.createBlockStateDefinition(builder);
    }

    public static Crystal getCrystal(BlockState state) {
        return state.getValue(CRYSTAL);
    }

    public static int getCrystalColor(BlockState state) {
        return getCrystal(state).getColor();
    }

    public static int getColor(@NotNull BlockState blockState,
                               @Nullable BlockAndTintGetter blockAndTintGetter,
                        @Nullable BlockPos blockPos, int index) {
        if (index != 0) return -1;
        return getCrystalColor(blockState);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
                                                @NotNull BlockState blockState) {
        return new CrystalInfuserBlockEntity(blockPos, blockState);
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                         @NotNull BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CrystalInfuserBlockEntity blockEntity) {
                ItemStackHandler inventory = blockEntity.getInventory();
                for (int index = 0; index < inventory.getSlots(); index++) {
                    ItemStack stackDrop = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(level, pos.getX() + 0.5D,
                            pos.getY() + 0.5D, pos.getZ() + 0.5D, stackDrop);
                    level.addFreshEntity(entity);
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.getBlockEntity(pos) instanceof CrystalInfuserBlockEntity infuser) {
            if (infuser.isInfusing()) {
                infuser.completeInfuse();
            } else {
                particleExplosion(state, pos, level);
            }
        }
        super.tick(state, level, pos, random);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
    }

    public static void giantCompassParticle(BlockState state, Level level, CrystalInfuserBlockEntity infuser) {
        if (!state.hasProperty(FACING)) return;

        Direction facing = state.getValue(FACING).getOpposite();
        Vec3 center = infuser.getStainedGlassCenterPos().getCenter();
        float orientation = infuser.getNextTempleOrientation();

        float blockYaw = facing.toYRot() * Mth.DEG_TO_RAD;
        float localAngle = orientation - blockYaw;

        Vec3 forward = Vec3.atLowerCornerOf(facing.getNormal()).normalize();
        Vec3 up = new Vec3(0, 1, 0);

        Vec3 right = up.cross(forward).normalize();
        up = forward.cross(right).normalize();

        Vec3 dir = right.scale(Mth.cos(localAngle))
                .add(up.scale(Mth.sin(localAngle)))
                .normalize();

        for (int i = 0; i < 25; i++) {
            Vec3 from = center.offsetRandom(level.random, 1.5F);
            from = from.add(dir.scale(level.random.nextFloat() * 6F));

            ModParticleUtils.addClientParticle(level,
                    new ShineParticleOptions(Vec3.ZERO, getCrystalColor(state)),
                    from, dir.scale(0.2F));
        }
    }


    public static void ambientParticles(BlockState state, BlockPos pos, Level level) {
        if (!state.hasProperty(FACING)) return;
        Direction facing = state.getValue(FACING);
        BlockPos minPos = pos.relative(facing.getOpposite(), 9)
                .relative(facing.getClockWise(), 10).below(3);
        BlockPos maxPos = pos.relative(facing, 3)
                .relative(facing.getClockWise(), -10).above(8);
        for (int i = 0; i < 22; i++) {
            Vec3 partPos = new Vec3(
                    level.random.nextFloat() * (maxPos.getX() - minPos.getX()) + minPos.getX(),
                    level.random.nextFloat() * (maxPos.getY() - minPos.getY()) + minPos.getY(),
                    level.random.nextFloat() * (maxPos.getZ() - minPos.getZ()) + minPos.getZ()
            );
            ModParticleUtils.addStaticClientParticle(level,
                    new ShineParticleOptions(Vec3.ZERO, getCrystalColor(state)), partPos);
        }
    }

    public static void particleGlyph(BlockState state, BlockPos pos, Level level) {
        if (!state.hasProperty(FACING)) return;
        double baseX = pos.getX();
        double baseY = pos.getY();
        double baseZ = pos.getZ();
        Vec3 center = positions[4].scale(1.0 / 16.0).add(baseX, baseY, baseZ);

        Vec3 axis = state.getValue(FACING) == Direction.NORTH
                || state.getValue(FACING) == Direction.SOUTH ?
                new Vec3(0, 0, 1)
                : new Vec3(1, 0, 0);

        ModParticleUtils.particleCircle(level,
                new GlyphParticleRotatingOptions(center, axis, 0.015F, getCrystalColor(state)),
                center, 1.0F, 10, axis);

        ModParticleUtils.particleCircle(level,
                 new GlyphParticleRotatingOptions(center, axis, -0.017F, getCrystalColor(state)),
                center, 1.4F, 12, axis);

        ModParticleUtils.particleCircle(level,
                new GlyphParticleRotatingOptions(center, axis, 0.019F, getCrystalColor(state)),
                center, 1.8F, 14, axis);

    }

    public static void particleShine(BlockState state, BlockPos pos, Level level, RandomSource random) {

        double baseX = pos.getX();
        double baseY = pos.getY();
        double baseZ = pos.getZ();

        Vec3 center = positions[4].scale(1.0F / 16.0F).add(baseX, baseY, baseZ);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < random.nextInt(1, 2); j++) {
                Vec3 from = positions[i].scale(1.0 / 16.0).add(baseX, baseY, baseZ);
                Vec3 dir = center.subtract(from).normalize().scale(shineSpeed
                        + (random.nextDouble() - 0.5D) * shineSpeed / 2D);

                level.addParticle(new ShineParticleOptions(center, getCrystalColor(state)),
                        from.x, from.y, from.z,
                        dir.x, dir.y, dir.z
                );
            }
        }
    }

    public static void particleExplosion(BlockState state, BlockPos pos, Level level) {
        if (!level.isClientSide) {
            double baseX = pos.getX();
            double baseY = pos.getY();
            double baseZ = pos.getZ();
            Vec3 center = positions[4].scale(1.0F / 16.0F).add(baseX, baseY, baseZ);
            PacketHandler.sendToAllClient(new
                    ParticleShineExplosion(center, 0.1F, explosionSpeed,
                    100, getCrystalColor(state)));
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type){
        return !level.isClientSide() ?
                (level0, pos0, state0, blockEntity) -> ((CrystalInfuserBlockEntity)blockEntity).tick() :
                (level0, pos0, state0, blockEntity) -> ((CrystalInfuserBlockEntity)blockEntity).clientTick();
    }
}
