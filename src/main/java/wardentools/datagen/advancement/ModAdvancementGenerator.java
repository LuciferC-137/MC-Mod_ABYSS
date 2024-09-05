package wardentools.datagen.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.worldgen.dimension.ModDimensions;

import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        Items.SCULK, // Picture displayed
                        Component.translatable("advancements.wardentools.root.title"), // Title
                        Component.translatable("advancements.wardentools.root.description"), // Description
                        new ResourceLocation(ModMain.MOD_ID, "textures/gui/advancements/backgrounds/sculk.png"), // Background
                        AdvancementType.TASK, //  (TASK, GOAL, or CHALLENGE)
                        true, // Show to everyone when completed
                        true, // Show in toast
                        false // Announce in chat
                )
                // Advancement criterion
                .addCriterion("has_sculk", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SCULK))
                .save(saver, new ResourceLocation(ModMain.MOD_ID, "root"));

        // Second advancement triggering after root
        AdvancementHolder corruptedHeart = Advancement.Builder.advancement()
                .parent(root) // This is child of root
                .display(
                        ItemRegistry.WARDEN_HEART.get(),
                        Component.translatable("advancements.wardentools.corruptedheart.title"),
                        Component.translatable("advancements.wardentools.corruptedheart.description"),
                        null, // background
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("has_warden_heart",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.WARDEN_HEART.get()))
                .save(saver, new ResourceLocation(ModMain.MOD_ID, "corrupted_heart"));

        AdvancementHolder abyssdiver = Advancement.Builder.advancement()
                .parent(corruptedHeart)
                .display(
                        ItemRegistry.ABYSS_DIVER.get(),
                        Component.translatable("advancements.wardentools.abyssdiver.title"),
                        Component.translatable("advancements.wardentools.abyssdiver.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_abyssdiver",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.ABYSS_DIVER.get()))
                .save(saver, new ResourceLocation(ModMain.MOD_ID, "abyssdiver"));

        AdvancementHolder theAbyss = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ItemRegistry.ECHO_CRISTAL.get(),
                        Component.translatable("advancements.wardentools.theabyss.title"),
                        Component.translatable("advancements.wardentools.theabyss.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("to_the_abyss",
                        ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.ABYSS_LEVEL_KEY))
                .save(saver, new ResourceLocation(ModMain.MOD_ID, "the_abyss"));
    }
}
