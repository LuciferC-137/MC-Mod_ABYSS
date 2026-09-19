package wardentools.items.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RadianceCatalystRecipeInput implements Container {

    private final ItemStack input;

    public RadianceCatalystRecipeInput(ItemStack input) {
        this.input = input;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return input.isEmpty();
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return slot == 0 ? input : ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        if (slot != 0) {
            return ItemStack.EMPTY;
        }

        return input.split(amount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        if (slot != 0) {
            return ItemStack.EMPTY;
        }

        return input;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        // Not needed for recipe matching.
    }

    @Override
    public void setChanged() {
        // Not needed for recipe matching.
    }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.@NotNull Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        // Not needed for recipe matching.
    }
}

