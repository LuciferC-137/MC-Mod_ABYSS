package wardentools.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.advancement.criteria.AbyssPortalCriteria;
import wardentools.advancement.criteria.CorruptionVesselCriteria;
import wardentools.advancement.criteria.RadianceBringerCriteria;
import wardentools.advancement.criteria.SummonProtectorCriteria;
import wardentools.items.armors.ArmorRegistry;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;
import wardentools.worldgen.dimension.ModDimensions;

import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries,
                         @NotNull Consumer<AdvancementHolder> saver,
                         @NotNull ExistingFileHelper existingFileHelper) {
        HolderGetter<EntityType<?>> entityTypeHolderGetter
                = registries.lookupOrThrow(Registries.ENTITY_TYPE);
        AdvancementHolder abyss = Advancement.Builder.advancement()
                .display(
                        Items.SCULK, // Picture displayed
                        Component.translatable("advancements.wardentools.abyss.title"), // Title
                        Component.translatable("advancements.wardentools.abyss.description"), // Description
                        ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/advancements/backgrounds/sculk.png"), // Background
                        AdvancementType.TASK, //  (TASK, GOAL, or CHALLENGE)
                        true, // Show to everyone when completed
                        true, // Show in toast
                        false // Announce in chat
                )
                // Advancement criterion
                .addCriterion("has_sculk", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SCULK))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss"));

        // Second advancement triggering after abyss
        AdvancementHolder corruptedHeart = Advancement.Builder.advancement()
                .parent(abyss) // This is child of abyss
                .display(
                        ItemRegistry.WARDEN_HEART.get(),
                        Component.translatable("advancements.wardentools.corruptedheart.title"),
                        Component.translatable("advancements.wardentools.corruptedheart.description"),
                        null, // no background
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("has_warden_heart",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.WARDEN_HEART.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corrupted_heart"));

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
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssdiver"));

        AdvancementHolder windwhisperer = Advancement.Builder.advancement()
                .parent(corruptedHeart)
                .display(
                        ItemRegistry.WIND_WHISPERER.get(),
                        Component.translatable("advancements.wardentools.windwhisperer.title"),
                        Component.translatable("advancements.wardentools.windwhisperer.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("windwhisperer",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.WIND_WHISPERER.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "windwhisperer"));

        AdvancementHolder theAbyss = Advancement.Builder.advancement()
                .parent(abyss)
                .display(
                        ItemRegistry.DARKGRASS_BLOCK.get(),
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
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "the_abyss"));

        AdvancementHolder cristals = Advancement.Builder.advancement()
                .parent(theAbyss)
                .display(
                        ItemRegistry.CITRINE_FRAGMENT.get(),
                        Component.translatable("advancements.wardentools.cristals.title"),
                        Component.translatable("advancements.wardentools.cristals.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("cristals",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemRegistry.ECHO_BLOCK.get(),
                                ItemRegistry.PALE_CRISTAL_BLOCK.get(),
                                ItemRegistry.RUBY_BLOCK.get(),
                                ItemRegistry.CITRINE_BLOCK.get(),
                                ItemRegistry.MALACHITE_BLOCK.get(),
                                Items.AMETHYST_BLOCK))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cristals"));

        AdvancementHolder radiance = Advancement.Builder.advancement()
                .parent(theAbyss)
                .display(
                        ItemRegistry.RADIANCE_FRAGMENT.get(),
                        Component.translatable("advancements.wardentools.radiance.title"),
                        Component.translatable("advancements.wardentools.radiance.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("radiance",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.RADIANCE_FRAGMENT.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance"));

        AdvancementHolder radianceCatalyst = Advancement.Builder.advancement()
                .parent(radiance)
                .display(
                        ItemRegistry.RADIANCE_CATALYST.get(),
                        Component.translatable("advancements.wardentools.radiance_catalyst.title"),
                        Component.translatable("advancements.wardentools.radiance_catalyst.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("radiance_catalyst",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.RADIANCE_CATALYST.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_catalyst"));

        if (ModEffects.RADIANCE_BRINGER.getHolder().isPresent()) {
            AdvancementHolder radiantArmor = Advancement.Builder.advancement()
                    .parent(radianceCatalyst)
                    .display(
                            ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE.get(),
                            Component.translatable("advancements.wardentools.radiant_armor.title"),
                            Component.translatable("advancements.wardentools.radiant_armor.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("radiant_armor",
                            EffectsChangedTrigger.TriggerInstance.hasEffects(
                                    MobEffectsPredicate.Builder.effects().and(ModEffects.RADIANCE_BRINGER.getHolder().get())))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiant_armor"));
        }

        AdvancementHolder protectorInvoker = Advancement.Builder.advancement()
                .parent(radiance)
                .display(
                        ItemRegistry.PROTECTOR_INVOKER.get(),
                        Component.translatable("advancements.wardentools.protector_invoker.title"),
                        Component.translatable("advancements.wardentools.protector_invoker.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("protector_invoker",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.PROTECTOR_INVOKER.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_invoker"));

        AdvancementHolder protector = Advancement.Builder.advancement()
                .parent(protectorInvoker)
                .display(
                        ItemRegistry.PROTECTOR_HEART.get(),
                        Component.translatable("advancements.wardentools.protector.title"),
                        Component.translatable("advancements.wardentools.protector.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("protector",
                        SummonProtectorCriteria.TriggerInstance.summonProtector())
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector"));

        AdvancementHolder deepCristal = Advancement.Builder.advancement()
                .parent(theAbyss)
                .display(
                        ItemRegistry.DEEPCRISTAL.get(),
                        Component.translatable("advancements.wardentools.deepcristal.title"),
                        Component.translatable("advancements.wardentools.deepcristal.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("deepcristal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.DEEPCRISTAL.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal"));

        if (ModEffects.CORRUPTION_VESSEL.getHolder().isPresent()) {
            AdvancementHolder deepArmor = Advancement.Builder.advancement()
                    .parent(deepCristal)
                    .display(
                            ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get(),
                            Component.translatable("advancements.wardentools.deep_armor.title"),
                            Component.translatable("advancements.wardentools.deep_armor.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("deep_armor",
                            EffectsChangedTrigger.TriggerInstance.hasEffects(
                                    MobEffectsPredicate.Builder.effects().and(ModEffects.CORRUPTION_VESSEL.getHolder().get())))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deep_armor"));
        }

        AdvancementHolder incarnation = Advancement.Builder.advancement()
                .parent(deepCristal)
                .display(
                        ItemRegistry.CHISELED_ABYSSALITE.get(),
                        Component.translatable("advancements.wardentools.incarnation.title"),
                        Component.translatable("advancements.wardentools.incarnation.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("kill_contagion_incarnation",
                        KilledTrigger.TriggerInstance.playerKilledEntity(
                                EntityPredicate.Builder.entity().of(entityTypeHolderGetter,
                                        ModEntities.CONTAGION_INCARNATION.get())))
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "incarnation"));

        AdvancementHolder corruption_vessel = Advancement.Builder.advancement()
                .parent(incarnation)
                .display(
                        ItemRegistry.CORRUPTED_VESSEL.get(),
                        Component.translatable("advancements.wardentools.corruption_vessel.title"),
                        Component.translatable("advancements.wardentools.corruption_vessel.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("corruption_vessel",
                        CorruptionVesselCriteria.TriggerInstance.choseCorruption())
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corruption_vessel"));

        AdvancementHolder radiance_bringer = Advancement.Builder.advancement()
                .parent(incarnation)
                .display(
                        ItemRegistry.PURE_VESSEL.get(),
                        Component.translatable("advancements.wardentools.radiance_bringer.title"),
                        Component.translatable("advancements.wardentools.radiance_bringer.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("radiance_bringer",
                        RadianceBringerCriteria.TriggerInstance.choseRadiance())
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_bringer"));

        if (ModEffects.CORRUPTED.getHolder().isPresent()) {
            AdvancementHolder corrupted = Advancement.Builder.advancement()
                    .parent(deepCristal)
                    .display(
                            ItemRegistry.CORRUPTED_ESSENCE.get(),
                            Component.translatable("advancements.wardentools.corrupted.title"),
                            Component.translatable("advancements.wardentools.corrupted.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("corrupted",
                            EffectsChangedTrigger.TriggerInstance.hasEffects(
                                    MobEffectsPredicate.Builder.effects().and(ModEffects.CORRUPTED.getHolder().get())))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corrupted"));
        }

        AdvancementHolder escape = Advancement.Builder.advancement()
                .parent(abyssdiver)
                .display(
                        ItemRegistry.RADIANT_STAFF.get(),
                        Component.translatable("advancements.wardentools.escape.title"),
                        Component.translatable("advancements.wardentools.escape.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("escape", AbyssPortalCriteria.TriggerInstance.openPortal())
                .save(saver, ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "escape"));
    }
}
