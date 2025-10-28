package wardentools.datagen.utils;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import wardentools.datagen.ModRecipesGenerator;
import wardentools.items.recipe.RadianceCatalystRecipe;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


public class RadianceCatalystRecipeBuilder {
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private static final String recipe_type_id = "radiance_catalyst";


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

    public void save(RecipeOutput output, ResourceLocation id) {
        if (input.getItems().length == 0) {
            throw new IllegalStateException("No input item for recipe " + id);
        }
        this.criteria.put("has_item", ModRecipesGenerator.hasItem(Arrays.stream(input.getItems())
                .toList().getFirst().getItem()));
        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancementBuilder);
        this.criteria.forEach(advancementBuilder::addCriterion);
        output.accept(id, new RadianceCatalystRecipe(input, result, energyCost),
                advancementBuilder.build(id.withPrefix("recipes/"
                        + recipe_type_id + "/"))
        );
    }
}
