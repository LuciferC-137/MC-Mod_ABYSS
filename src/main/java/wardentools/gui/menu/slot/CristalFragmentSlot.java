package wardentools.gui.menu.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import wardentools.items.ItemRegistry;

public class CristalFragmentSlot extends SlotItemHandler {
	private final CristalType cristal;

	public CristalFragmentSlot(IItemHandler itemHandler,
							   int index, int xPosition, int yPosition, CristalType type) {
		super(itemHandler, index, xPosition, yPosition);
		this.cristal = type;
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
        return switch (cristal) {
            case CITRINE -> stack.is(ItemRegistry.CITRINE_FRAGMENT.get());
            case AMETHYST -> stack.is(Items.AMETHYST_SHARD);
            case PALE_SHARD -> stack.is(ItemRegistry.PALE_SHARD.get());
            case RUBY -> stack.is(ItemRegistry.RUBY_FRAGMENT.get());
            case MALACHITE -> stack.is(ItemRegistry.MALACHITE_FRAGMENT.get());
            case ECHO_SHARD -> stack.is(Items.ECHO_SHARD);
        };
	}

	public enum CristalType {
		CITRINE,
		AMETHYST,
		PALE_SHARD,
		RUBY,
		MALACHITE,
		ECHO_SHARD
	}

}