package wardentools.gui.menu.slot;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
import wardentools.items.ItemRegistry;

public class RadianceFragmentSlot extends SlotItemHandler {

	public RadianceFragmentSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return stack.is(ItemRegistry.RADIANCE_CRISTAL.get()) || stack.is(ItemRegistry.RADIANCE_FRAGMENT.get());
	}

}