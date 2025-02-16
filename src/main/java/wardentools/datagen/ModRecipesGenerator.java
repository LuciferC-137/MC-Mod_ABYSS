package wardentools.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.items.armors.ArmorRegistry;

import java.util.concurrent.CompletableFuture;

public class ModRecipesGenerator extends RecipeProvider implements IConditionBuilder {
    public ModRecipesGenerator(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {

        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider,
                                                               @NotNull RecipeOutput recipeOutput) {
            return new ModRecipesGenerator(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return "";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.TOOLS, ItemRegistry.ABYSS_DIVER.get(), 1)
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('V', ItemRegistry.CORRUPTED_VESSEL.get())
                .pattern("  V")
                .pattern(" D ")
                .pattern("D  ")
                .unlockedBy("has_item", has(ItemRegistry.CORRUPTED_VESSEL.get()))
                .save(this.output);

        shaped(RecipeCategory.TOOLS, ItemRegistry.ABYSS_DIVER.get(), 1)
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('V', ItemRegistry.CORRUPTED_VESSEL.get())
                .pattern("V  ")
                .pattern(" D ")
                .pattern("  D")
                .unlockedBy("has_item", has(ItemRegistry.CORRUPTED_VESSEL.get()))
                .save(this.output, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss_diver_reverse").toString());

        shaped(RecipeCategory.TOOLS, ItemRegistry.RADIANT_STAFF.get())
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('V', ItemRegistry.PURE_VESSEL.get())
                .pattern("  V")
                .pattern(" D ")
                .pattern("D  ")
                .unlockedBy("has_item", has(ItemRegistry.PURE_VESSEL.get()))
                .save(this.output);

        shaped(RecipeCategory.TOOLS, ItemRegistry.RADIANT_STAFF.get())
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('V', ItemRegistry.PURE_VESSEL.get())
                .pattern("V  ")
                .pattern(" D ")
                .pattern("  D")
                .unlockedBy("has_item", has(ItemRegistry.PURE_VESSEL.get()))
                .save(this.output, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiant_staff_reverse").toString());

        shaped(RecipeCategory.COMBAT, ItemRegistry.ABYSSAL_SCYTHE.get(), 1)
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('I', ItemRegistry.DEEPINGOTS.get())
                .pattern("III")
                .pattern("  D")
                .pattern("  D")
                .unlockedBy("has_item", has(ItemRegistry.DEEPINGOTS.get()))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ItemRegistry.RADIANT_SPEAR.get())
                 .define('R', ItemRegistry.RADIANCE_INGOTS.get())
                 .pattern("  R")
                 .pattern(" R ")
                 .pattern("R  ")
                 .unlockedBy("has_item", has(ItemRegistry.RADIANCE_INGOTS.get()))
                 .save(this.output);


        shaped(RecipeCategory.DECORATIONS, ItemRegistry.BLACK_LANTERN.get(), 1)
                .define('I', Items.IRON_NUGGET)
                .define('E', Items.ECHO_SHARD)
                .define('P', ItemRegistry.PALE_SHARD.get())
                .pattern("IEI")
                .pattern("EPE")
                .pattern("IEI")
                .unlockedBy("has_item", has(ItemRegistry.PALE_SHARD.get()))
                .unlockedBy("has_item", has(Items.ECHO_SHARD))
                .save(this.output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemRegistry.CITRINE_FRAGMENT.get()),
                        RecipeCategory.MISC,
                        Items.AMETHYST_SHARD,
                        0.1f, // Exp
                        50)             // ticks
                .unlockedBy("has_item", has(ItemRegistry.CITRINE_FRAGMENT.get()))
                .save(this.output);

        shapeless(RecipeCategory.MISC, ItemRegistry.CORRUPTED_ESSENCE.get(), 9)
                .requires(ItemRegistry.WARDEN_HEART.get())
                .unlockedBy("has_item", has(ItemRegistry.WARDEN_HEART.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.WARDEN_HEART.get(), 1)
                .define('E', ItemRegistry.CORRUPTED_ESSENCE.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_item", has(ItemRegistry.CORRUPTED_ESSENCE.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.CORRUPTED_VESSEL.get(), 1)
                .define('E', ItemRegistry.CORRUPTED_ESSENCE.get())
                .define('G', Items.GLASS)
                .define('P', ItemRegistry.PALE_SHARD.get())
                .pattern("GPG")
                .pattern("PEP")
                .pattern("GPG")
                .unlockedBy("has_item", has(ItemRegistry.CORRUPTED_ESSENCE.get()))
                .unlockedBy("has_item", has(ItemRegistry.PALE_SHARD.get()))
                .save(this.output);


        this.allStoneVariants(this.output, "abyssalite",
                ItemRegistry.ABYSSALITE.get(), ItemRegistry.ABYSSALITE_BRICKS.get(),
                ItemRegistry.ABYSSALITE_BRICKS_SLAB.get(), ItemRegistry.ABYSSALITE_BRICKS_STAIRS.get(),
                ItemRegistry.ABYSSALITE_BRICKS_WALL.get(), ItemRegistry.CHISELED_ABYSSALITE.get());

        this.crackedStoneCrafts(this.output, "cracked_abyssalite",
                ItemRegistry.ABYSSALITE.get(), ItemRegistry.CRACKED_ABYSSALITE_BRICKS.get(),
                ItemRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get(),
                ItemRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR.get(),
                ItemRegistry.CRACKED_ABYSSALITE_BRICKS_WALL.get());

        this.allWoodCrafts(this.output, "darktree",
                ItemRegistry.DARKTREE_LOG.get(), ItemRegistry.DARKTREE_PLANKS.get(), ItemRegistry.STRIPPED_DARKTREE_LOG.get(),
                ItemRegistry.DARKTREE_SLAB.get(), ItemRegistry.DARKTREE_STAIRS.get(),
                ItemRegistry.DARKTREE_FENCE.get(), ItemRegistry.DARKTREE_FENCE_GATE.get(),
                ItemRegistry.DARKTREE_DOOR.get(), ItemRegistry.DARKTREE_TRAPDOOR.get(),
                ItemRegistry.DARKTREE_BUTTON.get(),
                ItemRegistry.DARKTREE_WOOD.get(), ItemRegistry.STRIPPED_DARKTREE_WOOD.get(),
                ItemRegistry.DARKTREE_BOAT.get(), ItemRegistry.DARKTREE_CHEST_BOAT.get());

        this.allWoodCrafts(this.output, "whitetree",
                ItemRegistry.WHITETREE_LOG.get(), ItemRegistry.WHITETREE_PLANKS.get(), ItemRegistry.STRIPPED_WHITETREE_LOG.get(),
                ItemRegistry.WHITETREE_SLAB.get(), ItemRegistry.WHITETREE_STAIRS.get(),
                ItemRegistry.WHITETREE_FENCE.get(), ItemRegistry.WHITETREE_FENCE_GATE.get(),
                ItemRegistry.WHITETREE_DOOR.get(), ItemRegistry.WHITETREE_TRAPDOOR.get(),
                ItemRegistry.WHITETREE_BUTTON.get(),
                ItemRegistry.WHITETREE_WOOD.get(), ItemRegistry.STRIPPED_WHITETREE_WOOD.get(),
                ItemRegistry.WHITETREE_BOAT.get(), ItemRegistry.WHITETREE_CHEST_BOAT.get());

        this.craftsForCrystal(this.output, ItemRegistry.CITRINE.get(),
                ItemRegistry.CITRINE_BLOCK.get(), ItemRegistry.CITRINE_FRAGMENT.get());
        this.craftsForCrystal(this.output, ItemRegistry.MALACHITE.get(),
                ItemRegistry.MALACHITE_BLOCK.get(), ItemRegistry.MALACHITE_FRAGMENT.get());
        this.craftsForCrystal(this.output, ItemRegistry.RUBY.get(),
                ItemRegistry.RUBY_BLOCK.get(), ItemRegistry.RUBY_FRAGMENT.get());
        this.craftsForCrystal(this.output, ItemRegistry.PALE_CRISTAL.get(),
                ItemRegistry.PALE_CRISTAL_BLOCK.get(), ItemRegistry.PALE_SHARD.get());
        this.craftsForCrystal(this.output, ItemRegistry.ECHO_CRISTAL.get(),
                ItemRegistry.ECHO_BLOCK.get(), Items.ECHO_SHARD);

        shaped(RecipeCategory.DECORATIONS, ItemRegistry.DEEPCRISTAL.get(), 1)
                .define('F', ItemRegistry.DEEP_FRAGMENT.get())
                .pattern(" F ")
                .pattern("FFF")
                .pattern(" F ")
                .unlockedBy("has_item", has(ItemRegistry.DEEP_FRAGMENT.get()))
                .save(this.output);

        shaped(RecipeCategory.DECORATIONS, ItemRegistry.RADIANCE_CRISTAL.get(), 1)
                .define('F', ItemRegistry.RADIANCE_FRAGMENT.get())
                .pattern(" F ")
                .pattern("FFF")
                .pattern(" F ")
                .unlockedBy("has_item", has(ItemRegistry.RADIANCE_FRAGMENT.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.DARK_STICK.get(), 1)
                .define('P', ItemRegistry.DARKTREE_PLANKS.get())
                .pattern("P")
                .pattern("P")
                .pattern("P")
                .unlockedBy("has_item", has(ItemRegistry.DARKTREE_LOG.get()))
                .save(this.output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.DEEPBLOCK.get(), 1)
                .define('I', ItemRegistry.DEEPINGOTS.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .unlockedBy("has_item", has(ItemRegistry.DEEPINGOTS.get()))
                .save(this.output);

        shapeless(RecipeCategory.MISC, ItemRegistry.DEEPINGOTS.get(), 9)
                .requires(ItemRegistry.DEEPBLOCK.get())
                .unlockedBy("has_item", has(ItemRegistry.DEEPBLOCK.get()))
                .save(this.output);

        this.smeltAndBlast(this.output, ItemRegistry.DEEPCRISTAL.get(), ItemRegistry.DEEPINGOTS.get(),
                "deepingot", 2f, 400);
        this.smeltAndBlast(this.output, ItemRegistry.RADIANCE_CRISTAL.get(), ItemRegistry.RADIANCE_INGOTS.get(),
                "radiance_ingot", 2f, 400);

        this.fullArmorCraft(this.output, ItemRegistry.DEEPINGOTS.get(),
                ArmorRegistry.DEEPCRISTAL_HELMET.get(), ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get(),
                ArmorRegistry.DEEPCRISTAL_LEGGINGS.get(), ArmorRegistry.DEEPCRISTAL_BOOTS.get());
        this.fullArmorCraft(this.output, ItemRegistry.RADIANCE_INGOTS.get(),
                ArmorRegistry.RADIANCE_CRISTAL_HELMET.get(), ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE.get(),
                ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS.get(), ArmorRegistry.RADIANCE_CRISTAL_BOOTS.get());

        shapeless(RecipeCategory.FOOD, ItemRegistry.NOCTILURE_TREAT.get())
                .requires(ItemRegistry.DEEP_FRUIT.get())
                .requires(Items.SUGAR)
                .requires(ItemRegistry.BLUE_GLOW_BERRIES.get())
                .unlockedBy("has_item", has(ItemRegistry.DEEP_FRUIT.get()))
                .unlockedBy("has_item", has(ItemRegistry.BLUE_GLOW_BERRIES.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.PROTECTOR_HEART.get())
                .define('E', ItemRegistry.PURE_ESSENCE.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_item", has(ItemRegistry.PURE_ESSENCE.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.PROTECTOR_INVOKER.get())
                .define('D', ItemRegistry.DARK_STICK.get())
                .define('V', ItemRegistry.PURE_VESSEL.get())
                .define('P', ItemRegistry.WHITETREE_PLANKS.get())
                .pattern("D D")
                .pattern(" V ")
                .pattern("P P")
                .unlockedBy("has_item", has(ItemRegistry.PURE_VESSEL.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.RADIANCE_CATALYST.get())
                .define('C', ItemRegistry.RADIANT_CORE.get())
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GLASS)
                .pattern("GIG")
                .pattern("ICI")
                .pattern("GIG")
                .unlockedBy("has_item", has(ItemRegistry.RADIANT_CORE.get()))
                .unlockedBy("has_item", has(ItemRegistry.RADIANCE_FRAGMENT.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.RADIANT_CORE.get())
                .define('F', ItemRegistry.RADIANCE_FRAGMENT.get())
                .define('W', ItemRegistry.WHITE_SEED.get())
                .pattern("FFF")
                .pattern("FWF")
                .pattern("FFF")
                .unlockedBy("has_item", has(ItemRegistry.RADIANCE_FRAGMENT.get()))
                .unlockedBy("has_item", has(ItemRegistry.WHITE_SEED.get()))
                .save(this.output);

        shaped(RecipeCategory.TOOLS, ItemRegistry.WHISTLE.get(), 1)
                .define('S', ItemRegistry.DARKTREE_SLAB.get())
                .pattern("SSS")
                .pattern("SS ")
                .unlockedBy("has_item", has(ItemRegistry.DARKTREE_SLAB.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ItemRegistry.WIND_WHISPERER.get())
                .define('L', ItemRegistry.DARKTREE_LEAVES.get())
                .define('C', ItemRegistry.CORRUPTED_VESSEL.get())
                .pattern("LLL")
                .pattern("LCL")
                .pattern("LLL")
                .unlockedBy("has_item", has(ItemRegistry.CORRUPTED_VESSEL.get()))
                .save(this.output);
    }

    protected void allStoneVariants(@NotNull RecipeOutput recipeOutput, String name,
                                    @NotNull Item block, @NotNull Item brick, @NotNull Item slab,
                                    @Nullable Item stairs, @Nullable Item wall,
                                    @Nullable Item chiseled){
        shaped(RecipeCategory.BUILDING_BLOCKS, brick, 4)
                .define('B', block)
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
        SingleItemRecipeBuilder.stonecutting(
                Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, brick, 1)
                .unlockedBy("has_item", has(block))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_brick_stone_cutting").toString());
        shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                .define('B', brick)
                .pattern("BBB")
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
        SingleItemRecipeBuilder.stonecutting(
                Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, slab, 2)
                .unlockedBy("has_item", has(block))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_slab_stone_cutting").toString());
        SingleItemRecipeBuilder.stonecutting(
                Ingredient.of(brick), RecipeCategory.BUILDING_BLOCKS, slab, 2)
                .unlockedBy("has_item", has(block))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_slab_from_brick_stone_cutting").toString());
        if (stairs != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                    .define('B', brick)
                    .pattern("  B")
                    .pattern(" BB")
                    .pattern("BBB")
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, stairs, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_stairs_stone_cutting").toString());
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(brick), RecipeCategory.BUILDING_BLOCKS, stairs, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_stairs_from_brick_stone_cutting").toString());
        }
        if (wall != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
                    .define('B', brick)
                    .pattern("BBB")
                    .pattern("BBB")
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, wall, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_wall_stone_cutting").toString());
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(brick), RecipeCategory.BUILDING_BLOCKS, wall, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_wall_from_brick_stone_cutting").toString());
        }
        if (chiseled != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, chiseled, 1)
                    .define('B', slab)
                    .pattern("B")
                    .pattern("B")
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, chiseled, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"chiseled_" + name + "_stone_cutting").toString());
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(brick), RecipeCategory.BUILDING_BLOCKS, chiseled, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"chiseled_" + name + "_from_brick_stone_cutting").toString());
        }
    }

    protected void allWoodCrafts(@NotNull RecipeOutput recipeOutput, String name,
                                 @NotNull Item log, @NotNull Item plank, @NotNull Item stripedLog,
                                 @Nullable Item slab, @Nullable Item stairs, @Nullable Item fence,
                                 @Nullable Item fenceGate, @Nullable Item door, @Nullable Item trapdoor,
                                 @Nullable Item button, @Nullable Item wood, @Nullable Item strippedWood,
                                 @Nullable Item boat, @Nullable Item chestboat) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, plank, 4)
                .requires(log)
                .unlockedBy("has_item", has(log))
                .save(recipeOutput);
        shapeless(RecipeCategory.BUILDING_BLOCKS, plank, 4)
                .requires(stripedLog)
                .unlockedBy("has_item", has(log))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name + "_plank_from_striped_log").toString());
        if (slab != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                    .define('P', plank)
                    .pattern("PPP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (stairs != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                    .define('P', plank)
                    .pattern("  P")
                    .pattern(" PP")
                    .pattern("PPP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (fence != null) {
            shaped(RecipeCategory.DECORATIONS, fence, 3)
                    .define('P', plank)
                    .define('S', Items.STICK)
                    .pattern("PSP")
                    .pattern("PSP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (fenceGate != null) {
            shaped(RecipeCategory.REDSTONE, fenceGate, 3)
                    .define('P', plank)
                    .define('S', Items.STICK)
                    .pattern("SPS")
                    .pattern("SPS")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (door != null) {
            shaped(RecipeCategory.REDSTONE, door, 3)
                    .define('P', plank)
                    .pattern("PP")
                    .pattern("PP")
                    .pattern("PP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (trapdoor != null) {
            shaped(RecipeCategory.REDSTONE, trapdoor, 2)
                    .define('P', plank)
                    .pattern("PPP")
                    .pattern("PPP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (button != null) {
            shaped(RecipeCategory.REDSTONE, button, 1)
                    .define('P', plank)
                    .pattern("P")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (wood != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, wood, 1)
                    .define('P', log)
                    .pattern("PP")
                    .pattern("PP")
                    .unlockedBy("has_item", has(log))
                    .save(recipeOutput);
        }
        if (strippedWood != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, strippedWood, 1)
                    .define('P', stripedLog)
                    .pattern("PP")
                    .pattern("PP")
                    .unlockedBy("has_item", has(log))
                    .save(recipeOutput);
        }
        if (boat != null) {
            shaped(RecipeCategory.TRANSPORTATION, boat, 1)
                    .define('P', plank)
                    .pattern("P P")
                    .pattern("PPP")
                    .unlockedBy("has_item", has(plank))
                    .save(recipeOutput);
        }
        if (chestboat != null && boat != null) {
            shapeless(RecipeCategory.TRANSPORTATION, chestboat, 1)
                    .requires(boat)
                    .requires(Items.CHEST)
                    .unlockedBy("has_item", has(boat))
                    .save(recipeOutput);
        }
    }

    protected void craftsForCrystal(@NotNull RecipeOutput recipeOutput,
                                    @NotNull Item crystal, @NotNull Item block, @NotNull Item shard) {
        shaped(RecipeCategory.BUILDING_BLOCKS, block, 1)
                .define('S', shard)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_item", has(crystal))
                .unlockedBy("has_item", has(shard))
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
        shaped(RecipeCategory.DECORATIONS, crystal, 1)
                .define('S', shard)
                .pattern(" S ")
                .pattern("SSS")
                .pattern(" S ")
                .unlockedBy("has_item", has(crystal))
                .unlockedBy("has_item", has(shard))
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
        shapeless(RecipeCategory.MISC, shard, 9)
                .requires(block)
                .unlockedBy("has_item", has(crystal))
                .unlockedBy("has_item", has(shard))
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
    }

    protected void crackedStoneCrafts(@NotNull RecipeOutput recipeOutput, String name,
                                      @NotNull Item block, @NotNull Item cracked,
                                      @Nullable Item crackedSlab, @Nullable Item crackedStair,
                                      @Nullable Item crackedWall) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(block),
                        RecipeCategory.BUILDING_BLOCKS, cracked, 0.0f, 100)
                .unlockedBy("has_item", has(block))
                .save(recipeOutput);
        if (crackedSlab != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, crackedSlab, 6)
                    .define('C', cracked)
                    .pattern("CCC")
                    .unlockedBy("has_item", has(cracked))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(cracked), RecipeCategory.BUILDING_BLOCKS, crackedSlab, 2)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_slab_stone_cutting").toString());
        }
        if (crackedStair != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, crackedStair, 4)
                    .define('C', cracked)
                    .pattern("  C")
                    .pattern(" CC")
                    .pattern("CCC")
                    .unlockedBy("has_item", has(cracked))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(cracked), RecipeCategory.BUILDING_BLOCKS, crackedStair, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_stairs_stone_cutting").toString());
        }
        if (crackedWall != null) {
            shaped(RecipeCategory.BUILDING_BLOCKS, crackedWall, 6)
                    .define('C', cracked)
                    .pattern("CCC")
                    .pattern("CCC")
                    .unlockedBy("has_item", has(cracked))
                    .save(recipeOutput);
            SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(cracked), RecipeCategory.BUILDING_BLOCKS, crackedWall, 1)
                    .unlockedBy("has_item", has(block))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,name + "_wall_stone_cutting").toString());
        }
    }

    protected void smeltAndBlast(@NotNull RecipeOutput recipeOutput, @NotNull Item input,
                                 @NotNull Item output, String name, float exp, int time) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input),
                        RecipeCategory.MISC, output, exp, time)
                .unlockedBy("has_item", has(input))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name + "_smelting").toString());
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input),
                        RecipeCategory.MISC, output, exp, time / 2)
                .unlockedBy("has_item", has(input))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name + "_blasting").toString());
    }

    protected void fullArmorCraft(@NotNull RecipeOutput recipeOutput, @NotNull Item material,
                                  @NotNull Item helmet, @NotNull Item chestplate,
                                  @NotNull Item leggings, @NotNull Item boots) {
        shaped(RecipeCategory.COMBAT, helmet, 1)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy("has_item", has(material))
                .save(recipeOutput);
        shaped(RecipeCategory.COMBAT, chestplate, 1)
                .define('M', material)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy("has_item", has(material))
                .save(recipeOutput);
        shaped(RecipeCategory.COMBAT, leggings, 1)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("has_item", has(material))
                .save(recipeOutput);
        shaped(RecipeCategory.COMBAT, boots, 1)
                .define('M', material)
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("has_item", has(material))
                .save(recipeOutput);
    }
}
