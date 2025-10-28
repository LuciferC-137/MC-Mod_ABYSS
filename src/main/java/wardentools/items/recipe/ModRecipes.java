package wardentools.items.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModMain.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ModMain.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RadianceCatalystRecipe>> RADIANCE_RECIPE_SRZ =
            SERIALIZERS.register("radiance_catalyst", RadianceCatalystRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<RadianceCatalystRecipe>> RADIANCE_RECIPE_TYPE =
            TYPES.register("radiance_catalyst", () -> new RecipeType<RadianceCatalystRecipe>() {
                @Override
                public String toString() {
                    return "radiance_catalyst";
                }
            });

}
