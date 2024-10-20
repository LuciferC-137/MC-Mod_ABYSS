package wardentools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
	public static final FoodProperties DEEP_FRUIT_PROPERTIES =
			new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION,
							800), 1f).build();
	
	public static final FoodProperties WHITE_SEED_PROPPERTIES =
			new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION,
							80), 1f).build();

	public static final FoodProperties GLOW_BERRY_PROPERTIES =
			new FoodProperties.Builder().nutrition(4).fast()
			.saturationMod(3f).effect(() -> new MobEffectInstance(MobEffects.GLOWING,
							200), 1f).build();


}
