package wardentools.gui.menu.slot;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
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