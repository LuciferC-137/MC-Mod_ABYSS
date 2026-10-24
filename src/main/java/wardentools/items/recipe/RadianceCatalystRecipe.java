package wardentools.items.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

import javax.annotation.Nullable;


public record RadianceCatalystRecipe(Ingredient inputItem, ItemStack output, int energyCost)
        implements Recipe<RadianceCatalystRecipeInput> {

    private static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "radiance_catalyst");

    @Override
    public boolean matches(@NotNull RadianceCatalystRecipeInput input, Level level) {
        return !level.isClientSide()
                && inputItem.test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RadianceCatalystRecipeInput input, @NotNull RegistryAccess registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registries) {
        return output;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(inputItem);
        return ingredients;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.RADIANCE_RECIPE_SRZ.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.RADIANCE_RECIPE_TYPE.get();
    }

    public static class Serializer
            implements RecipeSerializer<RadianceCatalystRecipe> {

        @Override
        public @NotNull RadianceCatalystRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            int energyCost = GsonHelper.getAsInt(json, "energy_cost");

            return new RadianceCatalystRecipe(ingredient, output, energyCost);
        }

        @Override
        public @Nullable RadianceCatalystRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            int energyCost = buffer.readVarInt();

            return new RadianceCatalystRecipe(ingredient, output, energyCost);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, RadianceCatalystRecipe recipe) {
            recipe.inputItem.toNetwork(buffer);
            buffer.writeItem(recipe.output);
            buffer.writeVarInt(recipe.energyCost);
        }
    }
}

