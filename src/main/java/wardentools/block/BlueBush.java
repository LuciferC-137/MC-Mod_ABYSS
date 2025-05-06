package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.items.ItemRegistry;

public class BlueBush extends TallGrassBlock implements BonemealableBlock {
    public static final EnumProperty<BerryState> BERRY_STATE;

    static {
        BERRY_STATE = EnumProperty.create("berry_state", BerryState.class);
    }

    public BlueBush(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BERRY_STATE, BerryState.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BERRY_STATE);
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource randomSource,
                                @NotNull BlockPos pos, @NotNull BlockState state) {
        if (state.getValue(BERRY_STATE) == BerryState.NONE) {
            level.setBlock(pos, state.setValue(BERRY_STATE, BerryState.BLUE_BERRY), Block.UPDATE_ALL);
        } else {
            throwBlueGlowBerry(level, pos);
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (state.getValue(BERRY_STATE) == BerryState.BLUE_BERRY) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(BERRY_STATE, BerryState.NONE), Block.UPDATE_ALL);
                throwBlueGlowBerry((ServerLevel) level, pos);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
                                                       @NotNull Level level, @NotNull BlockPos pos,
                                                       @NotNull Player player, @NotNull InteractionHand hand,
                                                       @NotNull BlockHitResult hitResult) {
        if (state.getValue(BERRY_STATE) == BerryState.BLUE_BERRY) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(BERRY_STATE, BerryState.NONE), Block.UPDATE_ALL);
                throwBlueGlowBerry((ServerLevel) level, pos);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    private void throwBlueGlowBerry(ServerLevel level, BlockPos pos) {
        Vec3 direction = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5)
                .offsetRandom(level.random, 0.5F);
        ItemStack item = new ItemStack(ItemRegistry.BLUE_GLOW_BERRIES.get());
        ItemEntity itemEntity = new ItemEntity(level,
                direction.x(), direction.y(), direction.z(), item);
        itemEntity.setDefaultPickUpDelay();
        level.addFreshEntity(itemEntity);
    }

    public enum BerryState implements StringRepresentable {
        NONE,
        BLUE_BERRY;

        @Override
        public @NotNull String getSerializedName() {
            return this == BLUE_BERRY ? "blue_berry" : "none";
        }

        public String toString() {
            return this.getSerializedName();
        }
    }
}
