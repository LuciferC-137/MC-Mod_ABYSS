package wardentools.items.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record RadianceCatalystRecipeInput(ItemStack input) implements RecipeInput {

    @Override
    public @NotNull ItemStack getItem(int index) {
        return input;
    }

    @Override
    public int size() {return 1;}
}
