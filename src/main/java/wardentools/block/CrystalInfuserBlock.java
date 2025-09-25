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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.CrystalInfuserBlockEntity;
import wardentools.misc.Crystal;

public class CrystalInfuserBlock extends Block implements EntityBlock {
    public static final EnumProperty<Crystal> CRYSTAL;

    static {
        CRYSTAL = EnumProperty.create("crystal_index",
                Crystal.class);
    }

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
            }
        }
        super.tick(state, level, pos, random);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.getBlockEntity(pos) instanceof CrystalInfuserBlockEntity infuser) {
            if (infuser.isInfusing()) {
                particleSpring(state, pos, level, random);
            }
        }
        super.animateTick(state, level, pos, random);
    }

    private static void particleSpring(BlockState state, BlockPos pos, Level level, RandomSource random) {
        double px = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
        double py = pos.getY() + 1.0D + (random.nextDouble() - 0.5D) * 0.2D;
        double pz = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
        double vx = (random.nextDouble() - 0.5D) * 0.02D;
        double vy = (random.nextDouble() - 0.5D) * 0.02D;
        double vz = (random.nextDouble() - 0.5D) * 0.02D;
        level.addParticle(state.getValue(CRYSTAL).getShineParticle(),
                px, py, pz, vx, vy, vz);
    }
}
