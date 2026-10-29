package wardentools.datagen.utils;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.datagen.ModRecipesGenerator;
import wardentools.items.recipe.ModRecipes;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RadianceCatalystRecipeBuilder {

    private static final String RECIPE_TYPE_ID = "radiance_catalyst";

    private final Map<String, Criterion> criteria = new LinkedHashMap<>();
    private final Ingredient input;
    private final ItemStack result;
    private final int energyCost;

    public RadianceCatalystRecipeBuilder(Ingredient input, ItemStack result, int energyCost) {
        this.input = input;
        this.result = result;
        this.energyCost = energyCost;
    }

    public static RadianceCatalystRecipeBuilder catalyst(Ingredient input, ItemStack result, int energyCost) {
        return new RadianceCatalystRecipeBuilder(input, result, energyCost);
    }

    public RadianceCatalystRecipeBuilder unlockedBy(
            String name,
            Criterion criterion
    ) {
        this.criteria.put(name, criterion);
        return this;
    }

    public void save(Consumer<FinishedRecipe> output, ResourceLocation id) {
        if (input.getItems().length == 0) {
            throw new IllegalStateException(
                    "No input item for recipe " + id
            );
        }

        this.criteria.put("has_item",
                new Criterion(ModRecipesGenerator.hasItem(
                                Arrays.stream(input.getItems())
                                        .findFirst()
                                        .orElseThrow()
                                        .getItem()
                        )
                )
        );

        Advancement.Builder advancement = Advancement.Builder.advancement()
                .addCriterion(
                        "has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(id)
                )
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(RequirementsStrategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        output.accept(new Result(id, input, result, energyCost, advancement,
                id.withPrefix("recipes/" + RECIPE_TYPE_ID + "/")));
    }

    private record Result(ResourceLocation id, Ingredient input, ItemStack result, int energyCost,
                          Advancement.Builder advancement, ResourceLocation advancementId) implements FinishedRecipe {

            private Result(ResourceLocation id, Ingredient input, ItemStack result, int energyCost,
                           Advancement.Builder advancement, ResourceLocation advancementId) {
                this.id = id;
                this.input = input;
                this.result = result.copy();
                this.energyCost = energyCost;
                this.advancement = advancement;
                this.advancementId = advancementId;
            }

            @Override
            public void serializeRecipeData(JsonObject json) {
                json.add("input", input.toJson());

                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("item", BuiltInRegistries.ITEM.getKey(result.getItem()).toString());

                if (result.getCount() > 1) {
                    resultJson.addProperty("count", result.getCount());
                }

                json.add("result", resultJson);
                json.addProperty("energy_cost", energyCost);
            }

            @Override
            public @NotNull ResourceLocation getId() {
                return id;
            }

            @Override
            public @NotNull RecipeSerializer<?> getType() {
                return ModRecipes.RADIANCE_RECIPE_SRZ.get();
            }

            @Override
            public JsonObject serializeAdvancement() {
                return advancement.serializeToJson();
            }

            @Override
            @Nullable
            public ResourceLocation getAdvancementId() {
                return advancementId;
            }
        }
}
