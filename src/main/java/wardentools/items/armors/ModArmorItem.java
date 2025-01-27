package wardentools.items.armors;

import com.google.common.collect.ImmutableMap;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final ResourceLocation CORRUPTION_ADVANCEMENT
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corruption_vessel");
    private static final ResourceLocation RADIANCE_ADVANCEMENT
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_bringer");
    private static final int EFFECT_TIME = 242;
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModMaterials.DEEPCRISTAL, new MobEffectInstance(ModEffects.CORRUPTION_VESSEL.getHolder().get(),
                            EFFECT_TIME, 0,
                            false,false, true))
                    .put(ModMaterials.RADIANCE_CRISTAL, new MobEffectInstance(ModEffects.RADIANCE_BRINGER.getHolder().get(),
                            EFFECT_TIME, 0,
                            false,false, true)).build();

    public ModArmorItem(ArmorMaterial pMaterial, ArmorType pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if(!level.isClientSide()) {
            if(hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        if(hasCorrectArmorOn(mapArmorMaterial, player)) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        Equippable boots = player.getInventory().getArmor(0).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable leggings = player.getInventory().getArmor(1).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable chestplate = player.getInventory().getArmor(2).getComponents().get(DataComponents.EQUIPPABLE);
        Equippable helmet = player.getInventory().getArmor(3).getComponents().get(DataComponents.EQUIPPABLE);
        if (boots == null || leggings == null || chestplate == null || helmet == null) {
            return false;
        }
        if (boots.model().isEmpty() || leggings.model().isEmpty()
            || chestplate.model().isEmpty() || helmet.model().isEmpty()) return false;

        boolean allSameMaterial =
                helmet.model().get().equals(material.modelId())
                && chestplate.model().get().equals(material.modelId())
                && leggings.model().get().equals(material.modelId())
                && boots.model().get().equals(material.modelId());
        return allSameMaterial && playerHasAdvancement(material, player);
    }

    private boolean playerHasAdvancement(ArmorMaterial material, Player player) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            AdvancementHolder advancementHolder = null;
            if (material == ModMaterials.DEEPCRISTAL) {
                advancementHolder = serverPlayer.server.getAdvancements().get(CORRUPTION_ADVANCEMENT);
            } else if (material == ModMaterials.RADIANCE_CRISTAL) {
                advancementHolder = serverPlayer.server.getAdvancements().get(RADIANCE_ADVANCEMENT);
            }
            if (advancementHolder != null) {
                AdvancementProgress progress = serverPlayer.getAdvancements()
                        .getOrStartProgress(advancementHolder);
                return progress.isDone();
            }
        }
        return false;
    }
}
