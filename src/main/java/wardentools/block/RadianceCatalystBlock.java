package wardentools.block;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.items.ItemRegistry;

public class RadianceCatalystBlock extends Block implements EntityBlock {

	public RadianceCatalystBlock(Properties prop) {
		super(prop);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return BlockEntityRegistry.RADIANCE_CATALYST_BLOCK_ENTITY.get().create(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}

	@Override
	protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
														@NotNull BlockPos pos, @NotNull Player player,
														@NotNull BlockHitResult hitResult) {
		return this.use(level, pos, player);
	}

	@Override
	protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
													   @NotNull Level level, @NotNull BlockPos pos,
													   @NotNull Player player, @NotNull InteractionHand hand,
													   @NotNull BlockHitResult hitResult) {
		switch (this.use(level, pos, player)) {
			case InteractionResult.SUCCESS:
				return ItemInteractionResult.SUCCESS;
			case InteractionResult.FAIL:
				return ItemInteractionResult.FAIL;
			case InteractionResult.PASS:
				return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	public @NotNull InteractionResult use(Level level, BlockPos pos,
										  Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof RadianceCatalystBlockEntity)) return InteractionResult.PASS;
		if (level.isClientSide()) return InteractionResult.SUCCESS;
		if (player instanceof ServerPlayer sPlayer) {
			sPlayer.openMenu((MenuProvider)blockEntity, pos);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
																  @NotNull BlockEntityType<T> type){
		return level.isClientSide() ? null : (level0, pos0, state0, blockEntity)
				-> ((RadianceCatalystBlockEntity)blockEntity).tick();
	}

	@Override
	public void onRemove(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
						 @NotNull BlockState newState, boolean isMoving) {
		if (!level.isClientSide()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof RadianceCatalystBlockEntity blockEntity) {
	            ItemStack stack = new ItemStack(ItemRegistry.RADIANCE_CATALYST.get());
	            int energy = blockEntity.getEnergy().getEnergyStored();
	            CompoundTag tag = new CompoundTag();
	            tag.putInt("Energy", energy);
				stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
	            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5D,
	            		pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
	            level.addFreshEntity(itemEntity);
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
}
