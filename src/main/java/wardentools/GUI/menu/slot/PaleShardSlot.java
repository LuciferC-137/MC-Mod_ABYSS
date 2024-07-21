package wardentools.GUI.menu.slot;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import wardentools.items.ItemRegistry;

public class PaleShardSlot extends SlotItemHandler {

	public PaleShardSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return stack.is(ItemRegistry.PALE_FRAGMENT.get()) || stack.is(ItemRegistry.PALE_SHARD.get());
	}

}