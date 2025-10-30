package wardentools.items.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, ModMain.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, ModMain.MOD_ID);

    public static final Supplier<RecipeSerializer<RadianceCatalystRecipe>> RADIANCE_RECIPE_SRZ =
            SERIALIZERS.register("radiance_catalyst", RadianceCatalystRecipe.Serializer::new);
    public static final Supplier<RecipeType<RadianceCatalystRecipe>> RADIANCE_RECIPE_TYPE =
            TYPES.register("radiance_catalyst", () -> new RecipeType<RadianceCatalystRecipe>() {
                @Override
                public String toString() {
                    return "radiance_catalyst";
                }
            });

}
