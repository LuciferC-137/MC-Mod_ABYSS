package wardentools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
	public static final FoodProperties DEEP_FRUIT_PROPERTIES =
			new FoodProperties.Builder().nutrition(2)
					.saturationModifier(0.2f).build();
	
	public static final FoodProperties WHITE_SEED_PROPPERTIES =
			new FoodProperties.Builder().nutrition(2)
					.saturationModifier(0.2f).build();

	public static final FoodProperties BLUE_GLOW_BERRY_PROPERTIES =
			new FoodProperties.Builder().nutrition(4)
			.saturationModifier(3f).build();

	public static final Consumable DEEP_FRUIT_EFFECT = Consumables.defaultFood().onConsume(
			new ApplyStatusEffectsConsumeEffect(
					new MobEffectInstance(MobEffects.NIGHT_VISION, 800), 1.0F)).build();

	public static final Consumable WHITE_SEED_EFFECT = Consumables.defaultFood().onConsume(
			new ApplyStatusEffectsConsumeEffect(
					new MobEffectInstance(MobEffects.REGENERATION, 80), 1.0F)).build();

	public static final Consumable BLUE_GLOW_BERRY_EFFECT = Consumables.defaultFood().onConsume(
			new ApplyStatusEffectsConsumeEffect(
					new MobEffectInstance(MobEffects.GLOWING, 200), 1.0F)).build();


}
