package wardentools.GUI.menu;

import org.jetbrains.annotations.NotNull;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import wardentools.GUI.MenuRegistry;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.items.ItemRegistry;

public class RadianceCatalystMenu extends AbstractContainerMenu {
	private final RadianceCatalystBlockEntity blockEntity;
	private final ContainerLevelAccess levelAccess;
	
	public RadianceCatalystMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(containerId, playerInventory,
				playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()));
	}
	
	public RadianceCatalystMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
		super(MenuRegistry.RADIANCE_CATALYST_MENU.get(), containerId);
		if (blockEntity instanceof RadianceCatalystBlockEntity be) {
			this.blockEntity = be;
		} else {
			throw new IllegalStateException("Incorrect block entity class (%s) passed into RadianceCatalystMenu!"
					.formatted(blockEntity.getClass().getCanonicalName()));
		}
		
		this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
		
		createPlayerHotbar(playerInventory);
		createPlayerInventory(playerInventory);
		createBlockEntityInventory(be);
	}

	private void createBlockEntityInventory(RadianceCatalystBlockEntity be) {
		be.getOptional().ifPresent(inventory -> {
			addSlot(new SlotItemHandler(inventory, 0, 44, 36){
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return stack.is(ItemRegistry.CORRUPTED_ESSENCE.get());
				}
			});
			addSlot(new SlotItemHandler(inventory, 1, 80, 36));
			addSlot(new SlotItemHandler(inventory, 2, 116, 36));
		});
	}

	private void createPlayerInventory(Inventory playerInventory) {
		for (int row = 0; row <3; row++) {
			for (int column = 0; column<9; column++) {
				addSlot(new Slot(playerInventory, 9 + column + (row * 9), 8 + (column * 18), 84 + (row *18)));
			}
		}
	}

	private void createPlayerHotbar(Inventory playerInventory) {
		for (int column = 0; column<9; column++) {
			addSlot(new Slot(playerInventory, column, 8 + (column * 18), 142));
		}
		
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		Slot fromSlot = getSlot(index);
		ItemStack fromStack = fromSlot.getItem();
		if (fromStack.getCount() <= 0) {
			fromSlot.set(ItemStack.EMPTY);
		}
		if (!fromSlot.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack copyFromStack = fromStack.copy();
		if (index<36) {
			if(!moveItemStackTo(fromStack, 36, 39, false)) {
				return ItemStack.EMPTY;
			}
		} else if (index < 39) {
			if (!moveItemStackTo(fromStack, 0, 36, false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.err.println("Invalid slot index: " + index);
			return ItemStack.EMPTY;
		}
		fromSlot.setChanged();
		fromSlot.onTake(player, fromStack);
		
		return copyFromStack;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.levelAccess, player, BlockRegistry.RADIANCE_CATALYST.get());
	}
	
	public RadianceCatalystBlockEntity getBlockEntity() {
		return this.blockEntity;
	}
}
