package wardentools.items.armors;


import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial implements ArmorMaterial {

    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY =
            new EnumMap<>(ArmorItem.Type.class);

    static {
        BASE_DURABILITY.put(ArmorItem.Type.BOOTS, 13);
        BASE_DURABILITY.put(ArmorItem.Type.LEGGINGS, 15);
        BASE_DURABILITY.put(ArmorItem.Type.CHESTPLATE, 16);
        BASE_DURABILITY.put(ArmorItem.Type.HELMET, 11);
    }

    private final String name;
    private final int durabilityMultiplier;
    private final Map<ArmorItem.Type, Integer> protection;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;
    private final float toughness;
    private final float knockbackResistance;

    public ModArmorMaterial(String name, int durabilityMultiplier,
            Map<ArmorItem.Type, Integer> protection, int enchantability,
            SoundEvent equipSound, Supplier<Ingredient> repairIngredient, float toughness, float knockbackResistance) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protection = new EnumMap<>(protection);
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.repairIngredient = repairIngredient;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type) * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protection.getOrDefault(type, 0);
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}

