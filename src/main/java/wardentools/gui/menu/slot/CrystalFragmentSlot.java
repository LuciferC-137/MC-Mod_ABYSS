package wardentools.gui.menu.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.Crystal;

public class CrystalFragmentSlot extends SlotItemHandler {
	private final Crystal crystal;

	public CrystalFragmentSlot(IItemHandler itemHandler,
							   int index, int xPosition, int yPosition, Crystal crystal) {
		super(itemHandler, index, xPosition, yPosition);
		this.crystal = crystal;
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(crystal.getJewel());
	}

}