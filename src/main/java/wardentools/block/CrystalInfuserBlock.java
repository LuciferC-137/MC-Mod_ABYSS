package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
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
import wardentools.particle.options.ShineParticleOptions;

public class CrystalInfuserBlock extends Block implements EntityBlock {
    public static final EnumProperty<Crystal> CRYSTAL;
    private static final Vec3[] positions = new Vec3[] {
            new Vec3(10.5F, 10F + 0.35F, 10.5F),
            new Vec3(5.5F, 10F + 0.35F, 5.5F),
            new Vec3(10.5F, 10F + 0.35F, 5.5F),
            new Vec3(5.5F, 10F + 0.35F, 10.5F),
            new Vec3(8F, 17F, 8F)
    };
    private static final float shineSpeed = 0.04F;
    private static final float explosionSpeed = 0.07F;

    static {CRYSTAL = EnumProperty.create("crystal_index", Crystal.class);}

    public CrystalInfuserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any())
                .setValue(CRYSTAL, Crystal.getDefault()));
    }

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

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(CRYSTAL);
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
        if (level.getBlockEntity(pos) instanceof CrystalInfuserBlockEntity infuser) {
            if (infuser.isInfusing()) {
                particleShine(state, pos, level, random);
            }
        }
        super.animateTick(state, level, pos, random);
    }

    private static void particleShine(BlockState state, BlockPos pos, Level level, RandomSource random) {

        double baseX = pos.getX();
        double baseY = pos.getY();
        double baseZ = pos.getZ();

        Vec3 center = positions[4].scale(1.0 / 16.0).add(baseX, baseY, baseZ);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < random.nextInt(1, 4); j++) {
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

    private static void particleExplosion(BlockState state, BlockPos pos, Level level) {
        if (!level.isClientSide) {
            double baseX = pos.getX();
            double baseY = pos.getY();
            double baseZ = pos.getZ();
            Vec3 center = positions[4].scale(1.0 / 16.0).add(baseX, baseY, baseZ);
            PacketHandler.sendToAllClient(new
                    ParticleShineExplosion(center, 0.1F, explosionSpeed,
                    40, getCrystalColor(state)));
        }
    }
}
