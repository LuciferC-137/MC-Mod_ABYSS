package wardentools.items.armors;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

public enum DeepCristalMaterial implements ArmorMaterial {
	DEEPCRISTAL("deepcristal",  37, new int[]{3, 6, 8, 3}, 25,
			SoundEvents.ARMOR_EQUIP_NETHERITE, 1f, 0f, ()->Ingredient.of(ItemRegistry.DEEPINGOTS.get()));

	
    private final String name;
    private final int durabilityMultiplier;
    private final int[] portectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    
    private static final int[] BASE_DURABILITY = new int[]{11, 16, 16, 11};
    
    DeepCristalMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name; //name must start the modid then colon.
        this.durabilityMultiplier = durability;
        this.portectionAmounts = protection;
        this.enchantmentValue = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

	public static int[] getHealthPerSlot() {
		return BASE_DURABILITY;
	}

	public String getName() {
		return ModMain.MOD_ID + ":" + this.name;
	}

	public int getDurabilityMultiplier() {
		return durabilityMultiplier;
	}

	public int[] getSlotProtections() {
		return portectionAmounts;
	}

	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	public SoundEvent getSound() {
		return sound;
	}

	public float getToughness() {
		return toughness;
	}

	public float getKnockbackResistance() {
		return knockbackResistance;
	}


	public int getDurabilityForType(ArmorItem.Type pType) {
		return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
	}


	public int getDefenseForType(ArmorItem.Type pType) {
		return this.portectionAmounts[pType.ordinal()];
	}

	public SoundEvent getEquipSound() {
		return this.sound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}   
    
	
}
