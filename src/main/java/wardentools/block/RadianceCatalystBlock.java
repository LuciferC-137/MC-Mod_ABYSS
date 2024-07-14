package wardentools.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.ItemStackHandler;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.client.ClientHooks;

public class RadianceCatalystBlock extends HorizontalDirectionalBlock implements EntityBlock {
	public static final MapCodec<RadianceCatalystBlock> CODEC = simpleCodec(RadianceCatalystBlock::new);

	public RadianceCatalystBlock(Properties prop) {
		super(prop);
		registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityRegistry.RADIANCE_CATALYST_BLOCK_ENTITY.get().create(pos, state);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
	
	@Override
	public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand interactionHand, BlockHitResult result) {
		if (interactionHand != InteractionHand.MAIN_HAND) {return InteractionResult.PASS;}
		if (!level.isClientSide()) {return InteractionResult.SUCCESS;}
		
		BlockEntity be = level.getBlockEntity(pos);
		if (be instanceof RadianceCatalystBlockEntity) {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openRadianceCatalystScreen(pos));
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
			BlockEntityType<T> type){
		return level.isClientSide() ? null : (level0, pos0, state0, blockEntity)
				-> ((RadianceCatalystBlockEntity)blockEntity).tick();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!level.isClientSide()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof RadianceCatalystBlockEntity blockEntity) {
				ItemStackHandler inventory = blockEntity.getInventory();
				for (int index = 0; index < inventory.getSlots(); index++) {
					ItemStack stack = inventory.getStackInSlot(index);
					var entity = new ItemEntity(level, pos.getX() + 0.5D,
							pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
					level.addFreshEntity(entity);
				}
			}
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}
	
}
