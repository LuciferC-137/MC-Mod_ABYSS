package wardentools.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.gui.MenuRegistry;
import wardentools.gui.menu.slot.CristalFragmentSlot;

import java.util.Objects;

public class DysfunctionningCatalystMenu extends AbstractContainerMenu {
	private final DysfunctionningCatalystBlockEntity blockEntity;
	private final ContainerLevelAccess levelAccess;
	private final ContainerData data;

	public DysfunctionningCatalystMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(containerId, playerInventory,
				playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()),
				new SimpleContainerData(8));
	}

	public DysfunctionningCatalystMenu(int containerId, Inventory playerInventory,
                                       BlockEntity blockEntity, ContainerData data) {
		super(MenuRegistry.DYSFUNCTIONNING_CATALYST_MENU.get(), containerId);
		if (blockEntity instanceof DysfunctionningCatalystBlockEntity be) {
			this.blockEntity = be;
		} else {
			throw new IllegalStateException("Incorrect block entity class (%s) passed into DysfunctionningCatalystMenu!"
					.formatted(blockEntity.getClass().getCanonicalName()));
		}
		
		this.levelAccess = ContainerLevelAccess.create(Objects.requireNonNull(blockEntity.getLevel()),
				blockEntity.getBlockPos());
		this.data = data;
		
		createPlayerHotbar(playerInventory);
		createPlayerInventory(playerInventory);
		createBlockEntityInventory(be);
		
		addDataSlots(data);
	}

	private void createBlockEntityInventory(DysfunctionningCatalystBlockEntity be) {
		be.getInventoryOptional().ifPresent(inventory -> {
			addSlot(new CristalFragmentSlot(inventory, 0, 15, 15,
					CristalFragmentSlot.CristalType.CITRINE));
			addSlot(new CristalFragmentSlot(inventory, 1, 15, 42,
					CristalFragmentSlot.CristalType.AMETHYST));
			addSlot(new CristalFragmentSlot(inventory, 2, 15, 70,
					CristalFragmentSlot.CristalType.PALE_SHARD));
			addSlot(new CristalFragmentSlot(inventory, 3, 145, 15,
					CristalFragmentSlot.CristalType.RUBY));
			addSlot(new CristalFragmentSlot(inventory, 4, 145, 42,
					CristalFragmentSlot.CristalType.MALACHITE));
			addSlot(new CristalFragmentSlot(inventory, 5, 145, 70,
					CristalFragmentSlot.CristalType.ECHO_SHARD));
		});
	}

	private void createPlayerInventory(Inventory playerInventory) {
		for (int row = 0; row <3; row++) {
			for (int column = 0; column<9; column++) {
				addSlot(new Slot(playerInventory,
						9 + column + (row * 9), 8 + (column * 18), 105 + (row *18)));
			}
		}
	}

	private void createPlayerHotbar(Inventory playerInventory) {
		for (int column = 0; column<9; column++) {
			addSlot(new Slot(playerInventory, column, 8 + (column * 18), 163));
		}
	}

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
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
			if(!moveItemStackTo(fromStack, 36, 42, false)) {
				return ItemStack.EMPTY;
			}
		} else if (index < 42) {
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
	public boolean stillValid(@NotNull Player player) {
		return stillValid(this.levelAccess, player, BlockRegistry.DYSFUNCTIONNING_CATALYST.get());
	}
	
	public DysfunctionningCatalystBlockEntity getBlockEntity() {
		return this.blockEntity;
	}

	public int getCitrineProgression() {return this.data.get(0);}
	public int getAmethystProgression() {return this.data.get(1);}
	public int getPaleShardProgression() {return this.data.get(2);}
	public int getRubyProgression() {return this.data.get(3);}
	public int getMalachiteProgression() {return this.data.get(4);}
	public int getEchoShardProgression() {return this.data.get(5);}
	public int getTotalCharge() {return this.data.get(6);}
	public int getEyeProgression() {return this.data.get(7);}
	
}
