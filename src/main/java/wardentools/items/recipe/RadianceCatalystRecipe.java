package wardentools.items.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record RadianceCatalystRecipe(Ingredient inputItem, ItemStack output, int energyCost)
        implements Recipe<RadianceCatalystRecipeInput> {

    @Override
    public boolean matches(@NotNull RadianceCatalystRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        return inputItem.test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RadianceCatalystRecipeInput input,
                                       HolderLookup.@NotNull Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return output;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.RADIANCE_RECIPE_SRZ.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.RADIANCE_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<RadianceCatalystRecipe> {
        public static final MapCodec<RadianceCatalystRecipe> CODEC
                = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient")
                        .forGetter(RadianceCatalystRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result")
                        .forGetter(RadianceCatalystRecipe::output),
                Codec.INT.fieldOf("energy_cost")
                        .forGetter(RadianceCatalystRecipe::energyCost)
        ).apply(inst, RadianceCatalystRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RadianceCatalystRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, RadianceCatalystRecipe::inputItem,
                        ItemStack.STREAM_CODEC, RadianceCatalystRecipe::output,
                        ByteBufCodecs.VAR_INT, RadianceCatalystRecipe::energyCost,
                        RadianceCatalystRecipe::new);

        @Override
        public @NotNull MapCodec<RadianceCatalystRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, RadianceCatalystRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }


}
